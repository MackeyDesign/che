/*******************************************************************************
 * Copyright (c) 2012-2016 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package org.eclipse.che.ide.ext.git.client.status;

import org.eclipse.che.api.git.shared.Status;
import org.eclipse.che.api.promises.client.Operation;
import org.eclipse.che.api.promises.client.OperationException;
import org.eclipse.che.api.promises.client.PromiseError;
import org.eclipse.che.ide.ext.git.client.GitLocalizationConstant;
import org.eclipse.che.api.git.gwt.client.GitServiceClient;
import org.eclipse.che.ide.api.app.AppContext;
import org.eclipse.che.ide.api.app.CurrentProject;
import org.eclipse.che.ide.api.notification.NotificationManager;
import org.eclipse.che.ide.ext.git.client.outputconsole.GitOutputConsole;
import org.eclipse.che.ide.ext.git.client.outputconsole.GitOutputConsoleFactory;
import org.eclipse.che.ide.extension.machine.client.processes.ConsolesPanelPresenter;
import org.eclipse.che.ide.rest.AsyncRequestCallback;
import org.eclipse.che.ide.rest.StringUnmarshaller;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Iterables.contains;
import static org.eclipse.che.api.git.shared.StatusFormat.LONG;
import static org.eclipse.che.ide.api.notification.StatusNotification.Status.FAIL;

/**
 * Handler to process actions with displaying the status of the Git work tree.
 *
 * @author Ann Zhuleva
 */
@Singleton
public class StatusCommandPresenter {
    public static final String STATUS_COMMAND_NAME = "Git status";

    private final GitServiceClient        service;
    private final AppContext              appContext;
    private final GitOutputConsoleFactory gitOutputConsoleFactory;
    private final ConsolesPanelPresenter  consolesPanelPresenter;
    private final GitLocalizationConstant constant;
    private final NotificationManager     notificationManager;

    /**
     * Create presenter.
     */
    @Inject
    public StatusCommandPresenter(GitServiceClient service,
                                  AppContext appContext,
                                  GitOutputConsoleFactory gitOutputConsoleFactory,
                                  ConsolesPanelPresenter consolesPanelPresenter,
                                  GitLocalizationConstant constant,
                                  NotificationManager notificationManager) {
        this.service = service;
        this.appContext = appContext;
        this.gitOutputConsoleFactory = gitOutputConsoleFactory;
        this.consolesPanelPresenter = consolesPanelPresenter;
        this.constant = constant;
        this.notificationManager = notificationManager;
    }

    /** Show status. */
    public void showStatus() {
        final CurrentProject project = appContext.getCurrentProject();
        if (project == null) {
            return;
        }

        service.statusText(appContext.getWorkspaceId(), project.getRootProject(), LONG,
                           new AsyncRequestCallback<String>(new StringUnmarshaller()) {
                               @Override
                               protected void onSuccess(String result) {
                                   printGitStatus(result, project);
                               }

                               @Override
                               protected void onFailure(Throwable exception) {
                                   notificationManager.notify(constant.statusFailed(), FAIL, true, project.getRootProject());
                               }
                           });
    }

    /**
     * Print colored Git status to Output
     *
     * @param statusText
     *         text to be printed
     */
    private void printGitStatus(final String statusText, final CurrentProject project) {
        service.status(appContext.getWorkspaceId(), project.getRootProject()).then(new Operation<Status>() {
            @Override
            public void apply(Status arg) throws OperationException {
                GitOutputConsole console = gitOutputConsoleFactory.create(STATUS_COMMAND_NAME);
                String[] lines = statusText.split("\n");
                //Files that have staged and unstaged changes in the same time
                List<String> stagedAndUnstagedFiles = new ArrayList<>();
                for (String line : lines) {
                    if (line.contains("modified:")) {
                        String fileName = line.replaceAll(".*modified: *", "");
                        if (!stagedAndUnstagedFiles.contains(fileName) && contains(arg.getModified(), fileName) &&
                            contains(arg.getChanged(), fileName)) {
                            console.printInfo(line);
                            stagedAndUnstagedFiles.add(fileName);
                        } else if (contains(arg.getModified(), fileName) || contains(arg.getConflicting(), fileName)) {
                            console.printError(line);
                        } else {
                            console.printInfo(line);
                        }
                        continue;
                    }
                    if (line.contains("deleted:")) {
                        String fileName = line.replaceAll(".*deleted: *", "");
                        if (contains(arg.getMissing(), fileName)) {
                            console.printError(line);
                        } else {
                            console.printInfo(line);
                        }
                        continue;
                    }
                    if (line.startsWith("\t") || line.startsWith("#\t")) {
                        String untrackedItem = line.replaceAll(".*\t", "").replace("/", "");
                        if (contains(arg.getUntracked(), untrackedItem) || contains(arg.getUntrackedFolders(), untrackedItem)) {
                            console.printError(line);
                        } else {
                            console.printInfo(line);
                        }
                        continue;
                    }
                    console.print(line);
                }
                consolesPanelPresenter.addCommandOutput(appContext.getDevMachineId(), console);
            }
        }).catchError(new Operation<PromiseError>() {
            @Override
            public void apply(PromiseError arg) throws OperationException {
                notificationManager.notify(constant.statusFailed(), FAIL, true, project.getRootProject());
            }
        });
    }
}
