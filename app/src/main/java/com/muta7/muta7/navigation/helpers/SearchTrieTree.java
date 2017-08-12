package com.muta7.muta7.navigation.helpers;

import com.muta7.muta7.database.models.Space;

import java.util.ArrayList;

/**
 * Created by Kareem Waleed on 3/20/2017.
 */

class TrieTreeNode {
    private TrieTreeNode[] children;
    ArrayList<Space> data;

    public TrieTreeNode() {
        children = new TrieTreeNode[27];
        data = new ArrayList<>();
    }

    public void addDataElement(Space dataElemenet) {
        data.add(dataElemenet);
    }

    public ArrayList<Space> getData() {
        return data;
    }

    public TrieTreeNode getChildbyIndex(int index) {
        if (index < 0 || index >= 27)
            return null;
        return children[index];
    }

    public TrieTreeNode initializeChildByIndex(int index) {
        if (index < 0 || index >= 27)
            return null;
        if (children[index] == null) {
            children[index] = new TrieTreeNode();
            return children[index];
        }
        return children[index];
    }
}

public class SearchTrieTree {
    private TrieTreeNode root;
    private ArrayList<Space> dataSet;

    public SearchTrieTree(ArrayList<Space> dataSet) {
        root = new TrieTreeNode();
        this.dataSet = dataSet;
        insertDataElements();
    }


    private void insertDataElements() {
        for (int i = 0; i < dataSet.size(); i++) {
            Space tempString = dataSet.get(i);
            char tempChar = tempString.getGeneralInfo().getSpaceName().charAt(0);
            tempChar = Character.toLowerCase(tempChar);
            int index = (int) (tempChar - 97);
            if(index == -65)
                insertDataElement(0, tempString, root.initializeChildByIndex(26));
            else
                insertDataElement(0, tempString, root.initializeChildByIndex(index));
        }
    }

    private void insertDataElement(int letterIndex, Space dataElement, TrieTreeNode node) {
        if (letterIndex >= dataElement.getGeneralInfo().getSpaceName().length())
            return;
        node.addDataElement(dataElement);
        letterIndex++;
        if (letterIndex >= dataElement.getGeneralInfo().getSpaceName().length())
            return;
        char tempChar = dataElement.getGeneralInfo().getSpaceName().charAt(letterIndex);
        tempChar = Character.toLowerCase(tempChar);
        int index = (int) (tempChar - 97);
        if(index == -65)
            insertDataElement(letterIndex, dataElement, node.initializeChildByIndex(26));
        else
            insertDataElement(letterIndex, dataElement,
                node.initializeChildByIndex(index));
    }

    public ArrayList<Space> search(String query) {
        if(query.equals(""))
            return null;
        char tempChar = query.charAt(0);
        tempChar = Character.toLowerCase(tempChar);
        int index = (int) (tempChar - 97);
        if(index == -65)
            return search(0, query, root.getChildbyIndex(26));
        return search(0, query, root.getChildbyIndex(index));
    }

    private ArrayList<Space> search(int litterIndex, String query, TrieTreeNode node) {
        if (node == null)
            return null;
        litterIndex++;
        if (litterIndex >= query.length())
            return node.getData();
        char tempChar = query.charAt(litterIndex);
        tempChar = Character.toLowerCase(tempChar);
        int index = (int) (tempChar - 97);
        if(index == -65)
            return search(litterIndex, query, node.getChildbyIndex(26));
        return search(litterIndex, query, node.getChildbyIndex(index));
    }
}
