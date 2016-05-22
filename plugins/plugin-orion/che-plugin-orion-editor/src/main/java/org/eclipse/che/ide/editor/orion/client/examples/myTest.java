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

import com.google.gwt.editor.client.Editor;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import org.eclipse.che.ide.api.action.Action;
import org.eclipse.che.ide.api.action.ActionEvent;

import com.google.inject.Inject;
import org.eclipse.che.ide.api.editor.EditorAgent;
import org.eclipse.che.ide.api.editor.EditorPartPresenter;
import org.eclipse.che.ide.api.editor.EditorProvider;
import org.eclipse.che.ide.api.editor.EditorRegistry;
import org.eclipse.che.ide.api.editor.annotation.AnnotationModel;
import org.eclipse.che.ide.api.editor.annotation.AnnotationModelEvent;
import org.eclipse.che.ide.api.editor.texteditor.TextEditorPresenter;
import org.eclipse.che.ide.api.notification.NotificationManager;
import org.eclipse.che.ide.editor.orion.client.OrionEditorModule;
import org.eclipse.che.ide.editor.orion.client.OrionEditorWidget;
import org.eclipse.che.ide.editor.orion.client.OrionTextEditorFactory;
import org.eclipse.che.ide.editor.orion.client.jso.OrionAnnotationModelOverlay;
import org.eclipse.che.ide.editor.orion.client.jso.OrionAnnotationOverlay;
import org.eclipse.che.ide.editor.orion.client.jso.OrionStyleOverlay;

import javax.validation.constraints.NotNull;


public class myTest extends Action {
    private NotificationManager notificationManager;
    private EditorAgent editorAgent;
    private AnnotationModelEvent annotationModelEvent;
    private OrionTextEditorFactory orionTextEditorFactory;
    private EditorRegistry editorRegistry;
    private EditorProvider editorProvider;
    private EditorPartPresenter editorPartPresenter;
    private OrionEditorModule orionEditorModule;

    @Inject
    public myTest(MyResources resources, NotificationManager notificationManager,EditorAgent editorAgent,OrionEditorModule orionEditorModule) {
        super("My Action", "My Action Description", null, resources.MyProjectTypeIcon());
        this.notificationManager = notificationManager;
        this.editorAgent = editorAgent;
        this.orionEditorModule = orionEditorModule;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.editorPartPresenter = editorAgent.getActiveEditor();
        String temp = editorPartPresenter.getEditorInput().getToolTipText();
        notificationManager.notify( temp);
       // editorAgent.getActiveEditor().close(true);
       // TextEditorPresenter<OrionEditorWidget> editor = (TextEditorPresenter<OrionEditorWidget>) editorAgent.getActiveEditor();
         //editorProvider.getEditor();
        final OrionAnnotationOverlay annotation = OrionAnnotationOverlay.create();

        OrionStyleOverlay styleOverlay = OrionStyleOverlay.create();
        //styleOverlay.setStyleClass(className);
        annotation.setStart(150);
        annotation.setEnd(150);
        annotation.setRangeStyle(styleOverlay);
        annotation.setType("che-marker");
//        private OrionEditorViewOverlay editorViewOverlay = new OrionEditorViewOverlay();
//        private OrionEditorOverlay editor = OrionEditorViewOverlay.get
//        final OrionAnnotationOverlay annotation = OrionAnnotationOverlay.create();
//
//        OrionStyleOverlay styleOverlay = OrionStyleOverlay.create();
//        //styleOverlay.setStyleClass(className);
//
//        //int start = embeddedDocument.getIndexFromPosition(range.getFrom());
//        //int end = embeddedDocument.getIndexFromPosition(range.getTo());
//
//        annotation.setStart(150);
//        annotation.setEnd(150);
//        annotation.setRangeStyle(styleOverlay);
//        annotation.setType("che-marker");
//
//
//        editorOverlay.getAnnotationModel().addAnnotation(annotation);
////        final OrionAnnotationOverlay annotation = OrionAnnotationOverlay.create();
////
////        OrionStyleOverlay styleOverlay = OrionStyleOverlay.create();
////        styleOverlay.setStyleClass(className);
////
////        int start = embeddedDocument.getIndexFromPosition(range.getFrom());
////        int end = embeddedDocument.getIndexFromPosition(range.getTo());
////
////        annotation.setStart(start);
////        annotation.setEnd(end);
////        annotation.setRangeStyle(styleOverlay);
//        annotation.setType("pairProgramming");
 }
}
