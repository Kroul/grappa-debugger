package com.github.fge.grappa.debugger.mainwindow;

import com.github.fge.grappa.debugger.javafx.JavafxDisplay;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;

public class MainWindowDisplay
    extends JavafxDisplay<MainWindowPresenter>
{
    @FXML
    protected BorderPane pane;

    @FXML
    protected Label label;

    @FXML
    protected Label dbLoadStatus;

    @FXML
    protected ProgressBar dbLoadProgress;

    @FXML
    public Label dbLoadProgressMessage;

    @Override
    public void init()
    {
    }

    @FXML
    void newWindowEvent(final ActionEvent event)
    {
        presenter.handleNewWindow();
    }

    @FXML
    void closeWindowEvent(final ActionEvent event)
    {
        presenter.handleCloseWindow();
    }

    @FXML
    void loadFileEvent(final ActionEvent event)
    {
        presenter.handleLoadFile();
    }
}
