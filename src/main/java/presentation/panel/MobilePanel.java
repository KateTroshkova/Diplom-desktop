package presentation.panel;

import data.connection.ConnectionSettings;
import domain.model.DeviceInfo;
import domain.model.Screenshot;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import presentation.presenter.MobilePresenter;
import presentation.view.MobileView;

import java.io.File;

public class MobilePanel extends ImageView implements MobileView {

    private MobilePresenter presenter;

    private Label infoLabel;
    private Button sendButton;

    public MobilePanel() {
        presenter = new MobilePresenter(this);
        setOnMousePressed(this::onGestureStarted);
        setOnMouseReleased(this::onGestureFinished);
        setOnMouseDragged(this::onNewPoint);
    }

    public void setInfoLabel(Label infoLabel) {
        this.infoLabel = infoLabel;
    }

    public void setSendButton(Button sendButton) {
        this.sendButton = sendButton;
        this.sendButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose file");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"));
            File file = fileChooser.showOpenDialog(this.getScene().getWindow());
            presenter.sendFile(file);
        });
    }

    private void onGestureStarted(MouseEvent event) {
        presenter.startGesture(new Point2D(event.getX()*2, event.getY()*2));
    }

    private void onGestureFinished(MouseEvent event) {
        presenter.endGesture(new Point2D(event.getX()*2, event.getY()*2));
    }

    private void onNewPoint(MouseEvent event) {
        presenter.nextGesture(new Point2D(event.getX()*2, event.getY()*2));
    }

    @Override
    public void updateImage(Screenshot screenshot) {
        if (screenshot.getScreenshot()!=null) {
            setFitWidth(screenshot.getWidth() / 2.0);
            setFitHeight(screenshot.getHeight() / 2.0);
            setImage(screenshot.getScreenshot());
        }
    }

    @Override
    public void setInfo(DeviceInfo info) {
        infoLabel.setText(info.toString());
    }
}
