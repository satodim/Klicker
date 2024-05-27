package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;

public class ShopScreen implements Screen {

    private Stage stage;
    private BitmapFont font;
    private TextureAtlas buttonsAtlas;
    boolean isTouchDownLeft;
    boolean isTouchDownRight;

    private Skin buttonSkin;
    private TextButton button;
    private BitmapFont font2;
    private TextureAtlas buttonsAtlas2;
    private final MyGdxGame myGdxGame;
    private Skin buttonSkin2;
    private TextButton button2;
    private BitmapFont font4;
    private TextureAtlas buttonsAtlas4;
    private Skin buttonSkin4;
    private TextButton button4;
    int count;
    int points = 1;
    PointCounter pointCounter;
    public Character character;
    Texture characterTexture;
    Texture backgroundTexture;
    ScreenGame screenGame;

    int currentTextureIndexToRight = 0;
    int currentTextureIndexLeft = 0;

    final int pointCounterMarginRight = 400;
    final int pointCounterMarginTop = 60;
    ShopScreen(MyGdxGame myGdxGame) {
        screenGame = new ScreenGame(myGdxGame);

        //массив текстур передвижения вправо
        Texture textureToRight1 = new Texture("An01_F00.png");
        Texture textureToRight2 = new Texture("An01_F01.png");
        Texture textureToRight3 = new Texture("An01_F02.png");
        Texture textureToRight4 = new Texture("An01_F03.png");
        Texture textureToRight5 = new Texture("An01_F04.png");
        Texture textureToRight6 = new Texture("An01_F05.png");
        Texture textureToRight7 = new Texture("An01_F06.png");
        Texture textureToRight8 = new Texture("An01_F07.png");
        Texture textureToRight9 = new Texture("An01_F08.png");
        Texture textureToRight10 = new Texture("An01_F09.png");
        Texture textureToRight11= new Texture("An01_F10.png");
        Texture textureToRight12= new Texture("An01_F11.png");
        Texture textureToRight13 = new Texture("An01_F12.png");
        Texture textureToRight14 = new Texture("An01_F13.png");
        Texture textureToRight15= new Texture("An01_F14.png");
        Texture textureToRight16= new Texture("An01_F15.png");
        Texture[] texturesToRight = new Texture[]{textureToRight1, textureToRight2, textureToRight3,textureToRight4, textureToRight5, textureToRight6,textureToRight7, textureToRight8, textureToRight9,textureToRight10, textureToRight11, textureToRight12,textureToRight13,textureToRight14,textureToRight15,textureToRight16};


        Texture textureToLeft1 = new Texture("An01_F00 (1).png");
        Texture textureToLeft2 = new Texture("An01_F01 (1).png");
        Texture textureToLeft3 = new Texture("An01_F02 (1).png");
        Texture textureToLeft4 = new Texture("An01_F03 (1).png");
        Texture textureToLeft5 = new Texture("An01_F04 (1).png");
        Texture textureToLeft6 = new Texture("An01_F05 (1).png");
        Texture textureToLeft7 = new Texture("An01_F06 (1).png");
        Texture textureToLeft8 = new Texture("An01_F07 (1).png");
        Texture textureToLeft9 = new Texture("An01_F08 (1).png");
        Texture textureToLeft10 = new Texture("An01_F09 (1).png");
        Texture textureToLeft11 = new Texture("An01_F10 (1).png");
        Texture textureToLeft12 = new Texture("An01_F11 (1).png");
        Texture textureToLeft13 = new Texture("An01_F12 (1).png");
        Texture textureToLeft14 = new Texture("An01_F13 (1).png");
        Texture textureToLeft15 = new Texture("An01_F14 (1).png");
        Texture textureToLeft16 = new Texture("An01_F15 (1).png");
        Texture[] texturesToLeft = new Texture[]{textureToLeft1, textureToLeft2, textureToLeft3,textureToLeft4, textureToLeft5, textureToLeft6,textureToLeft7, textureToLeft8, textureToLeft9,textureToLeft10, textureToLeft11, textureToLeft12,textureToLeft13,textureToLeft14,textureToLeft15,textureToLeft16};

        character = new Character(100, 120, characterTexture, 0, 500, 500);
        characterTexture = new Texture("An01_F00.png");
        pointCounter = new PointCounter(SCR_WIDTH - pointCounterMarginRight, SCR_HEIGHT - pointCounterMarginTop);
        backgroundTexture = new TextureRegion(new Texture("Example.jpg"), 0, 0, 2048, 563).getTexture();
        this.myGdxGame = myGdxGame;

        // кнопка передвижения вправо
        buttonsAtlas = new TextureAtlas("buttons.pack");
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas);
        font = new BitmapFont();
        font.getData().scale(5f);
        font.setColor(Color.WHITE);
        stage = new Stage();
        stage.clear();


        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = buttonSkin.getDrawable("ButtonOff");
        style.down = buttonSkin.getDrawable("ButtonOn");
        style.font = font;

        button = new TextButton("", style);
        button.setPosition(350, 50);
        button.setHeight(100);
        button.setWidth(150);
        button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                character.setisStanding(false);
                isTouchDownRight = true;
                new Thread(() -> {
                    while (isTouchDownRight) {
                        character.setrunToRight(true);
                        character.moveToRight();
                        character.setTexture(texturesToRight[currentTextureIndexToRight]);
                        characterTexture = texturesToRight[currentTextureIndexToRight];
                        currentTextureIndexToRight = ++currentTextureIndexToRight % texturesToRight.length;
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();
                return true;

            }


            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isTouchDownRight = false;
                currentTextureIndexToRight = 0;
                character.setrunToRight(false);
                character.setisStanding(true);
                character.isCharacterStanding();

            }

        });

        buttonsAtlas2 = new TextureAtlas("buttons2.pack");
        buttonSkin2 = new Skin();
        buttonSkin2.addRegions(buttonsAtlas2);
        font2 = new BitmapFont();
        font2.getData().scale(5f);
        font2.setColor(Color.WHITE);


        TextButton.TextButtonStyle style2 = new TextButton.TextButtonStyle();
        style2.up = buttonSkin2.getDrawable("ButtonOn");
        style2.down = buttonSkin2.getDrawable("ButtonOff");
        style2.font = font2;

        button2 = new TextButton("", style2);
        button2.setPosition(100, 50);
        button2.setHeight(100);
        button2.setWidth(150);
        button2.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                character.setisStanding(false);
                isTouchDownLeft = true;
                new Thread(() -> {
                    while (isTouchDownLeft) {
                        character.setrunToLeft(true);
                        character.moveToLeft();
                        character.setTexture(texturesToLeft[currentTextureIndexLeft]);
                        characterTexture = texturesToLeft[currentTextureIndexLeft];
                        currentTextureIndexLeft = ++currentTextureIndexLeft % texturesToLeft.length;
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();

                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isTouchDownLeft = false;
                currentTextureIndexLeft = 0;
                character.setrunToLeft(false);
                character.setisStanding(true);
                character.isCharacterStanding();
            }

        });
        buttonsAtlas4 = new TextureAtlas("buttons.pack");
        buttonSkin4 = new Skin();
        buttonSkin4.addRegions(buttonsAtlas4);
        font4 = new BitmapFont();
        font4.getData().scale(5f);
        font4.setColor(Color.WHITE);


        TextButton.TextButtonStyle style4 = new TextButton.TextButtonStyle();
        style4.up = buttonSkin4.getDrawable("ButtonOff");
        style4.down = buttonSkin4.getDrawable("ButtonOn");
        style4.font = font4;

        button4 = new TextButton("", style4);
        button4.setPosition(1000, 200);
        button4.setHeight(300);
        button4.setWidth(300);
        button4.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (count >=1000){
                    count-= 1000;
                    points *= 2;
                }
                else{
                    count -= 0;
                }
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }

        });
        stage.addActor(button);
        stage.addActor(button2);
        stage.addActor(button4);


    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        int checkedCount = screenGame.getCountToCheck();
        count = checkedCount;
        int checkedX = character.getXToCheck();
        if (checkedX <= 50){
            myGdxGame.setScreen(myGdxGame.screenGame);
            character.setX(1400);
        }
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        myGdxGame.batch.draw(backgroundTexture, 0, 0);
        myGdxGame.batch.draw(backgroundTexture, 0, Gdx.graphics.getHeight());
        pointCounter.draw(myGdxGame.batch,count);
        pointCounter.draw(myGdxGame.batch,count);
        character.draw(myGdxGame.batch);
        stage.draw();
        myGdxGame.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        character.dispose();
        buttonSkin.dispose();
        buttonsAtlas.dispose();
        font.dispose();
        stage.dispose();
        buttonSkin2.dispose();
        buttonsAtlas2.dispose();
        font2.dispose();
        buttonSkin4.dispose();
        buttonsAtlas4.dispose();
        font4.dispose();
        pointCounter.dispose();

    }
}
