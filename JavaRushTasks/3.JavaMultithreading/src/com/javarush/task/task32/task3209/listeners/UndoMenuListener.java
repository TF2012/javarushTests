package com.javarush.task.task32.task3209.listeners;

import com.javarush.task.task32.task3209.View;
import com.javarush.task.task32.task3209.actions.UndoAction;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class UndoMenuListener implements MenuListener {
    private View view;
    private JMenuItem undoMenuItem;
    private JMenuItem redoMenuItem;

    public UndoMenuListener(View view, JMenuItem undoMenuItem, JMenuItem redoMenuItem) {
        this.view = view;
        this.undoMenuItem = undoMenuItem;
        this.redoMenuItem = redoMenuItem;
    }
    @Override
    public void menuSelected(MenuEvent e) {
        System.out.println("we can undo action?");
        view.canUndo();
        undoMenuItem.setEnabled(view.canUndo());
        redoMenuItem.setEnabled(view.canRedo());
    }
    @Override
    public void menuDeselected(MenuEvent e) {

    }
    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
