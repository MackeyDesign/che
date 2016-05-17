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

import org.eclipse.che.ide.api.action.Action;
import org.eclipse.che.ide.api.action.ActionEvent;

import com.google.inject.Inject;
import org.eclipse.che.ide.api.notification.NotificationManager;
import org.eclipse.che.ide.editor.orion.client.jso.OrionAnnotationOverlay;
import org.eclipse.che.ide.editor.orion.client.jso.OrionEditorOverlay;
import org.eclipse.che.ide.editor.orion.client.jso.OrionStyleOverlay;


public class myTest extends Action {
    private NotificationManager notificationManager;
    @Inject
    public myTest(MyResources resources, NotificationManager notificationManager) {
        super("My Action", "My Action Description", null, resources.MyProjectTypeIcon());
        this.notificationManager = notificationManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        notificationManager.notify("Hello World test now !");
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
