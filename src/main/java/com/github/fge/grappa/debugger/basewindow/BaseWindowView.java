package com.github.fge.grappa.debugger.basewindow;

import com.github.fge.grappa.debugger.legacy.tracetab.TraceTabPresenter;

import java.nio.file.Path;

public interface BaseWindowView
{
    void injectTab(TraceTabPresenter presenter);

    Path chooseFile();

    void setWindowTitle(String windowTitle);

    void showError(String header, String message, Throwable throwable);

    void setLabelText(String text);
}
