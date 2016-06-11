package org.eclipse.che.ide.editor.orion.client.examples;

/**
 *

 Copyright (c) 2012-2016 Codenvy, S.A.
 All rights reserved. This program and the accompanying materials
 are made available under the terms of the Eclipse Public License v1.0
 which accompanies this distribution, and is available at
 http://www.eclipse.org/legal/epl-v10.html

 Contributors:
 Codenvy, S.A. - initial API and implementation

 */

import com.google.gwt.core.client.GWT;
import org.eclipse.che.ide.api.action.Action;
import org.eclipse.che.ide.api.action.ActionEvent;

import com.google.inject.Inject;
import org.eclipse.che.ide.api.editor.EditorAgent;
import org.eclipse.che.ide.api.editor.EditorPartPresenter;
import org.eclipse.che.ide.api.editor.text.TextPosition;
import org.eclipse.che.ide.api.editor.text.TextRange;
import org.eclipse.che.ide.api.editor.texteditor.TextEditorPresenter;
import org.eclipse.che.ide.api.notification.Notification;
import static org.eclipse.che.ide.api.notification.ReadState.READ;
import org.eclipse.che.ide.api.notification.NotificationManager;
import org.eclipse.che.ide.api.notification.ReadState;
import org.eclipse.che.ide.api.notification.StatusNotification;
import org.eclipse.che.ide.editor.orion.client.PairProgrammingResources;
import static org.eclipse.che.ide.api.notification.StatusNotification.Status.PROGRESS;
import static org.eclipse.che.ide.api.notification.StatusNotification.DisplayMode.FLOAT_MODE;



public class myTest extends Action {
    private NotificationManager notificationManager;
    private EditorAgent editorAgent;
    private EditorPartPresenter activeEditor;
    private TextEditorPresenter textEditor;
    private PairProgrammingResources RESOURCES;

    @Inject
    public myTest(MyResources resources, NotificationManager notificationManager, EditorAgent editorAgent, PairProgrammingResources pairProgrammingResources) {
        super("Multi Cursor", "My Action Description", null, resources.MyProjectTypeIcon());
        this.notificationManager = notificationManager;
        this.editorAgent = editorAgent;
        this.RESOURCES = pairProgrammingResources;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //final CustomListBoxResources RESOURCES = GWT.create(CustomListBoxResources.class);
        this.activeEditor = editorAgent.getActiveEditor();
        if (activeEditor instanceof TextEditorPresenter){
             this.textEditor = (TextEditorPresenter)activeEditor;
        }
        int pos = textEditor.getDocument().getCursorOffset();
        final TextPosition from = textEditor.getDocument().getPositionFromIndex(pos);
        final TextPosition to = textEditor.getDocument().getPositionFromIndex(pos);

        final TextRange textRange = new TextRange(from, to);
        //String css = RESOURCES.getCSS().listBox();
        //textEditor.getDocument().setCursorPosition(to);
        //String annotationStyle = customListBoxResources.getCSS().listBox();
        //String annotationStyle = RESOURCES.getCSS().pairProgramminig();
        String annotationStyle = RESOURCES.getCSS().pairProgramminigRemove();
//        textEditor.getHasTextMarkers().addMarker(textRange,annotationStyle);
//        annotationStyle = RESOURCES.getCSS().yoda();
//        textEditor.getHasTextMarkers().addMarker(textRange,annotationStyle);
//        annotationStyle = RESOURCES.getCSS().classSwitch();
        textEditor.getHasTextMarkers().addMarker(textRange,annotationStyle);

          Notification notification = new Notification("my test message");
          StatusNotification statusNotification = new StatusNotification("my status",PROGRESS,FLOAT_MODE);
//          notification.setContent("just a test");
//          notificationManager.notify(notification);
        statusNotification.setState(READ);
        notificationManager.notify(statusNotification);
        notificationManager.notify("Cursor at "+pos);
 }
}
