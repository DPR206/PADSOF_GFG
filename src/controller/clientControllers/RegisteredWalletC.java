package controller.clientControllers;

import controller.Controller;
import controller.browserControllers.MixedBrowseMyWalletC;
import model.product.SecondHandProduct;
import model.store.Store;
import model.user.RegisteredClient;
import view.App;
import view.clientPanels.RegisteredWalletP;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.nio.file.*;

/**
 * The type Registered wallet c.
 * @author Ana O.R.
 * @version 1.0
 */
public class RegisteredWalletC implements Controller {
    private final App frame;
    private final Store model;
    private final RegisteredWalletP view;
    private final MixedBrowseMyWalletC walletBrowserController;
    private String photoPath;

    /**
     * Instantiates a new Registered wallet c.
     * @param frame the frame
     * @param model the model
     * @param view  the view
     */
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public RegisteredWalletC(App frame, Store model, RegisteredWalletP view) {
        this.frame = frame;
        this.model = model;
        this.view = view;

        this.walletBrowserController = new MixedBrowseMyWalletC(frame, model, view.getBrowseMyWalletP());

        initializeActions();
    }

    @Override
    public void initializeActions() {
        view.getAddProduct().addActionListener((e) -> {
            if (view.getProductTypeCmbBox().getSelectedIndex() != -1 && view.getNameField().getText() != null &&
                view.getDescriptionField().getText() != null) {
                SecondHandProduct product =
                        new SecondHandProduct(view.getNameField().getText(), view.getDescriptionField().getText(),
                                photoPath, view.getType(), view.getClient());
                ((RegisteredClient) frame.getUser()).addProductWallet(product);

                JOptionPane.showMessageDialog(frame, "Product added successfully!");

                this.walletBrowserController.refreshData();
                this.walletBrowserController.initializeActionsForMiniPanels();
            }
        });

        view.getPhotoChooser().addActionListener((e) -> {
            JFileChooser photoChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
            photoChooser.setFileFilter(filter);

            if (photoChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = photoChooser.getSelectedFile();
                JOptionPane.showMessageDialog(frame, "Selected file: " + selectedFile.getName());

                String destinationFolder = ".\\resources\\data\\productImages\\";
                photoPath = destinationFolder + selectedFile.getName();
                File destinationFile = new File(photoPath);

                try {
                    Path sourcePath = selectedFile.toPath();
                    Path targetPath = destinationFile.toPath();

                    Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error copying the photo: " + ex.getMessage(), "File Error",
                            JOptionPane.ERROR_MESSAGE);
                    photoPath = null;
                }
            }
        });
    }
}