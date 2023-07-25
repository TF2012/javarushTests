package com.javarush.task.task32.task3209;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;
    public Controller(View view) {
        this.view = view;
    }

    public static void main(String[] args) {
        View v = new View();
        Controller c = new Controller(v);
        v.setController(c);
        v.init();
        c.init();
    }
    public void init() { createNewDocument(); }

    public void exit() {
        System.exit(0);
    }
    public HTMLDocument getDocument() { return document; }
    public void resetDocument() {
        if (document != null) {
            document.removeUndoableEditListener(view.getUndoListener());
        }
        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }
    public void setPlainText(String text) {
        try {
            resetDocument();
            StringReader stringReader = new StringReader(text);
            new HTMLEditorKit().read(stringReader, document, 0);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }
    public String getPlainText() {
        StringWriter stringWriter = new StringWriter();
        try {
            new HTMLEditorKit().write(stringWriter, document, 0, document.getLength());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
        return stringWriter.toString();
    }
    public void createNewDocument() {
        view.selectHtmlTab();
        this.resetDocument();
        view.setTitle("New HTML redactor");
        currentFile = null;
    }
    public void openDocument() {
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        HTMLFileFilter htmlFileFilter = new HTMLFileFilter();
        jFileChooser.setFileFilter(htmlFileFilter);
        int choose = jFileChooser.showOpenDialog(view);
        if (choose == JFileChooser.APPROVE_OPTION) {
            currentFile = jFileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            int chose = jFileChooser.showSaveDialog(view);
            if (chose == JFileChooser.APPROVE_OPTION) {
                try {
                    FileReader fileReader = new FileReader(currentFile);
                    HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
                    htmlEditorKit.read(fileReader, document, 0);
                    view.resetUndo();
                } catch (Exception e) {
                    ExceptionHandler.log(e);
                }
            }
        }
    }
    public void saveDocument() {
        view.selectHtmlTab();
        if (currentFile != null) {
            view.setTitle(currentFile.getName());
            try {
                FileWriter fileFilter = new FileWriter(currentFile);
                HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
                htmlEditorKit.write(fileFilter, document, 0, document.getLength());
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        } else {
            saveDocumentAs();
        }
    }
    public void saveDocumentAs() {
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        HTMLFileFilter htmlFileFilter = new HTMLFileFilter();
        jFileChooser.setFileFilter(htmlFileFilter);
        int choose = jFileChooser.showSaveDialog(view);
        if (choose == JFileChooser.APPROVE_OPTION) {
            currentFile = jFileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try {
                FileWriter fileFilter = new FileWriter(currentFile);
                HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
                htmlEditorKit.write(fileFilter, document, 0, document.getLength());
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }


    }
}