package org.eclipse.che.ide.editor.orion.client;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * Created by rnavagamuwa on 5/23/16.
 */
public interface PairProgrammingResources extends ClientBundle {
    @Source({"pair-programming.css", "org/eclipse/che/ide/api/ui/style.css"})
    CSS getCSS();

    interface CSS extends CssResource {


        @ClassName("proposal-noemphasis-title-keywords")
        String proposalNoemphasisTitleKeywords();

        String textviewTooltip();

        String textviewRightRuler();

        String currentBracket();

        String constant();

        @ClassName("proposal-noemphasis-title")
        String proposalNoemphasisTitle();

        String folding();

        String preprocessor();

        String textViewFind();

        String currentLinkedGroup();

        String annotations();

        String language();

        String ruler();

        String textviewInnerRightRuler();

        @ClassName("keyword-operator")
        String keywordOperator();

        String rulerLines();

        String matchingBracket();

        String selectedLinkedGroup();

        String textviewScroll();

        String checked();

        @ClassName("entity-name-tag")
        String entityNameTag();

        String warning();

        String block();

        String tag();

        String textviewSelectionUnfocused();

        String keyword();

        String selected();

        String annotationRange();

        String overlay();

        String documentation();

        String matchingSearch();

        String tooltipImage();

        String meta();

        String textviewZoom();

        String textViewTooltipOnHover();

        String textviewContent();

        String contentassist();

        String annotationHTML();

        String tooltipTheme();

        @ClassName("proposal-name")
        String proposalName();

        String string();

        String collapsed();

        @ClassName("proposal-hr")
        String proposalHr();

        String error();

        String tooltipTitle();

        String textviewBlockCursor();

        String expanded();

        String parameter();

        String orionCodenvy();

        String currentSearch();

        @ClassName("entity-other-attribute-name")
        String entityOtherAttributeName();

        String lines();

        String currentLine();

        String annotationLine();

        String annotation();

        String overview();

        String rulerZoomWindow();

        String textViewFindCloseButton();

        String zoom();

        String textview();

        String textviewLeftRuler();

        String textViewFindButton();

        String annotationOverview();

        String tooltipRow();

        String variable();

        String comment();
    }
}
