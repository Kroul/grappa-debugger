package com.github.parboiled1.grappa.debugger;

import com.github.parboiled1.grappa.debugger.basewindow.BaseWindowPresenter;
import com.github.parboiled1.grappa.debugger.basewindow.BaseWindowUi;
import com.github.parboiled1.grappa.debugger.basewindow.BaseWindowView;
import com.github.parboiled1.grappa.debugger.basewindow.DefaultBaseWindowView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

// TODO: implement default exception handling
public final class TestApp
    extends Application
    implements BaseWindowFactory
{
    private final URL baseWindowFxml;

    private final Map<BaseWindowPresenter, Stage> windows = new HashMap<>();
    private final AtomicInteger windowCount = new AtomicInteger();

    public TestApp()
        throws IOException
    {
        baseWindowFxml = TestApp.class.getResource("/baseWindow.fxml");
        if (baseWindowFxml == null)
            throw new IOException("cannot load base window FXML file");
    }


    @Override
    public void start(final Stage primaryStage)
    {
        createWindow(primaryStage);
    }

    public static void main(final String... args)
    {
        launch(args);
    }

    @Override
    public void createWindow()
    {
        createWindow(new Stage());
    }

    private void createWindow(final Stage stage)
    {
        final FXMLLoader loader = new FXMLLoader(baseWindowFxml);
        final Pane pane;
        try {
            pane = loader.load();
        } catch (IOException e) {
            // TODO!
            throw new RuntimeException(e);
        }

        final BaseWindowUi ui = loader.getController();
        final BaseWindowView view = new DefaultBaseWindowView(ui);
        final BaseWindowPresenter presenter
            = new BaseWindowPresenter(this, view);

        ui.init(presenter);

        stage.setScene(new Scene(pane));
        stage.setTitle("window " + windowCount.getAndIncrement());

        windows.put(presenter, stage);

        stage.show();
    }

    @Override
    public void close(final BaseWindowPresenter presenter)
    {
        windows.get(presenter).close();
    }
}
