package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Node {

        private int x;
        private int y;
        private boolean hasTop , hasRight , hasBottom , hasLeft;

        public Node(int x, int y, boolean hasTop, boolean hasRight, boolean hasBottom, boolean hasLeft) {
            this.x = x;
            this.y = y;
            this.hasTop = hasTop;
            this.hasRight = hasRight;
            this.hasBottom = hasBottom;
            this.hasLeft = hasLeft;
        }


        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }


        public boolean isHasTop() {
            return hasTop;
        }

        public void setHasTop(boolean hasTop) {
            this.hasTop = hasTop;
        }

        public boolean isHasRight() {
            return hasRight;
        }

        public void setHasRight(boolean hasRight) {
            this.hasRight = hasRight;
        }

        public boolean isHasBottom() {
            return hasBottom;
        }

        public void setHasBottom(boolean hasBottom) {
            this.hasBottom = hasBottom;
        }

        public boolean isHasLeft() {
            return hasLeft;
        }

        public void setHasLeft(boolean hasLeft) {
            this.hasLeft = hasLeft;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }
