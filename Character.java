package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Character {
    public int x, y;
    int speed;
    int currentTextureIndexStanding = 0;
    int currentTextureIndexStandingToLeft = 0;
    int currentTextureIndexAttacking = 0;
    boolean runToRight;
    boolean runToLeft;
    boolean isStanding = true;
    boolean isStandingToLeft = true;
    boolean isAttacking = true;
    Texture[] texturesStanding;
    Texture[] texturesStandingToLeft;
    Texture[] texturesAttacking;

    Texture texture;
    int width, height;


    public Character(int x, int y, Texture texture, int speed, int width, int height) {
        this.x = x;
        this.y = y;
        this.texture = new Texture("An01_F00.png");
        this.speed = speed;
        this.width = width;
        this.height = height;

        Texture textureStanding1 = new Texture("R_An00_F00.png");
        Texture textureStanding2 = new Texture("R_An00_F01.png");
        Texture textureStanding3 = new Texture("R_An00_F02.png");
        Texture textureStanding4 = new Texture("R_An00_F03.png");
        Texture textureStanding5 = new Texture("R_An00_F04.png");
        Texture textureStanding6 = new Texture("R_An00_F05.png");
        texturesStanding = new Texture[]{textureStanding1, textureStanding2, textureStanding3, textureStanding4, textureStanding5, textureStanding6};

        Texture textureStandingToLeft1 = new Texture("L_An00_F00.png");
        Texture textureStandingToLeft2 = new Texture("L_An00_F01.png");
        Texture textureStandingToLeft3 = new Texture("L_An00_F02.png");
        Texture textureStandingToLeft4 = new Texture("L_An00_F03.png");
        Texture textureStandingToLeft5 = new Texture("L_An00_F04.png");
        Texture textureStandingToLeft6 = new Texture("L_An00_F05.png");
        texturesStandingToLeft = new Texture[]{textureStandingToLeft1, textureStandingToLeft2, textureStandingToLeft3, textureStandingToLeft4, textureStandingToLeft5, textureStandingToLeft6};

        Texture textureAttacking1 = new Texture("R_An03_F00.png");
        Texture textureAttacking2 = new Texture("R_An03_F01.png");
        Texture textureAttacking3 = new Texture("R_An03_F02.png");
        Texture textureAttacking4 = new Texture("R_An03_F03.png");
        Texture textureAttacking5 = new Texture("R_An03_F04.png");
        Texture textureAttacking6 = new Texture("R_An03_F05.png");
        Texture textureAttacking7 = new Texture("R_An03_F06.png");
        Texture textureAttacking8 = new Texture("R_An03_F07.png");
        Texture textureAttacking9 = new Texture("R_An03_F08.png");
        Texture textureAttacking10 = new Texture("R_An03_F09.png");
        texturesAttacking = new Texture[]{textureAttacking1, textureAttacking2, textureAttacking3,textureAttacking4,textureAttacking5,textureAttacking6,textureAttacking7,textureAttacking8,textureAttacking9,textureAttacking10};
    }
    public  int getXToCheck() {
        return x;
    }

    public void moveToRight() {
        if (runToRight) {
            speed = 15;
            x += speed;
        } else {
            speed = 0;
        }
    }
    public void moveToLeft() {
        if (runToLeft) {
            speed = 15;
            x -= speed;
            if (x<= -200){
                x = -200;
                speed = 0;
            }
        } else {
            speed = 0;
        }
    }
    public void runningToRight() {
        if (runToRight) {
            speed = 20;
            x += speed;
        } else {
            speed = 0;
        }
    }
    public void runningToLeft() {
        if (runToLeft) {
            speed = 20;
            x -= speed;
            if (x<= -200){
                x = -200;
                speed = 0;
            }
        } else {
            speed = 0;
        }
    }
    public void isCharacterStanding(){
        new Thread(() -> {
                while (isStanding){
                texture = texturesStanding[currentTextureIndexStanding];
                currentTextureIndexStanding = ++currentTextureIndexStanding % texturesStanding.length;
                    try {
                        Thread.sleep(130);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        }).start();

    }
    public void isCharacterStandingToLeft(){
        new Thread(() -> {
            while (isStandingToLeft){
                texture = texturesStandingToLeft[currentTextureIndexStandingToLeft];
                currentTextureIndexStandingToLeft = ++currentTextureIndexStandingToLeft % texturesStandingToLeft.length;
                try {
                    Thread.sleep(130);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }
    public void isCharacterAttacking(){
        new Thread(() -> {
            while (isAttacking){
                texture = texturesAttacking[currentTextureIndexAttacking];
                currentTextureIndexAttacking = ++currentTextureIndexAttacking % texturesAttacking.length;
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public void setrunToRight(boolean newrunToRight) {
        runToRight = newrunToRight;
    }
    public void setrunToLeft(boolean newrunToLeft) {
        runToLeft = newrunToLeft;
    }
    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    public void setisStanding(boolean newisStanding) {
        isStanding = newisStanding;
    }
    public void setisStandingToLeft(boolean newisStandingToLeft) {
        isStandingToLeft = newisStandingToLeft;
    }
    public void setisAttacking(boolean newisAttacking) {
        isAttacking = newisAttacking;
    }
    public void setcurrentTextureIndexAttacking(int newcurrentTextureIndexAttacking) {
        currentTextureIndexAttacking = newcurrentTextureIndexAttacking;
    }
    public void setX(int newX) {
        x = newX;
    }



    public void draw(Batch batch) {
        batch.draw(texture,x, y, width, height);
    }

    public void dispose() {
            texture.dispose();
    }
}