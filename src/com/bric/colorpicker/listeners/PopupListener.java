package com.bric.colorpicker.listeners;

import com.bric.colorpicker.contextualmenu.ContextualMenu;

import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopupListener extends MouseAdapter {

    private ContextualMenu contextualMenu;

    public PopupListener(ContextualMenu contextualMenu) {
        this.contextualMenu = contextualMenu;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        showPopupMenu(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        showPopupMenu(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        showPopupMenu(e);
    }

    private void showPopupMenu(final MouseEvent e) {
        if (!e.isPopupTrigger()) {
            return;
        }
        e.consume();
        SwingUtilities.invokeLater(() -> contextualMenu.showPopup(e.getComponent(), e.getX(), e.getY()));
    }
}
