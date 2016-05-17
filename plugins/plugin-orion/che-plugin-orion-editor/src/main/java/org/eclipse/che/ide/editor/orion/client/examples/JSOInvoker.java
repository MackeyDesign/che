package org.eclipse.che.ide.editor.orion.client.examples;

import org.eclipse.che.ide.editor.orion.client.jso.OrionEditorViewOverlay;
import org.eclipse.che.ide.editor.orion.client.jso.OrionEditorOverlay;

public class JSOInvoker extends OrionEditorViewOverlay{

    protected JSOInvoker() {
    }

    public final OrionEditorOverlay getActiveEditor()
    {
        return getEditor();
    }
}
