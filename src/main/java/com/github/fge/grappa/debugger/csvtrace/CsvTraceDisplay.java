package com.github.fge.grappa.debugger.csvtrace;

import com.github.fge.grappa.debugger.javafx.JavafxDisplay;
import com.github.fge.grappa.internal.NonFinalForTesting;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.ToolBar;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@NonFinalForTesting
public class CsvTraceDisplay
    extends JavafxDisplay<CsvTracePresenter>
{
    @FXML
    protected ToolBar toolbar;

    @FXML
    protected Button refresh;

    @FXML
    protected ProgressBar progressBar;

    @FXML
    protected Tab treeTab;

    @FXML
    protected Tab rulesTab;

    @FXML
    protected Tab matchesTab;

    @FXML
    protected Tab treeDepthTab;

    @Override
    public void init()
    {
    }

    public void tabsRefreshEvent(Event event)
    {
        presenter.handleTabsRefreshEvent();
    }
}
