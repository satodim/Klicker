package com.mygdx.game;


import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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



public class ScreenGame implements Screen {
    public  int getCountToCheck() {
        return count;
    }
    private Music music;
    private Stage stage;
    private BitmapFont font;
    private TextureAtlas buttonsAtlas;
    boolean isTouchDownLeft;
    boolean isTouchDownRight;

    private Skin buttonSkin;
    private TextButton button;
    private BitmapFont font2;
    private TextureAtlas buttonsAtlas2;
    private Skin buttonSkin2;
    private TextButton button2;
    private BitmapFont font3;
    private TextureAtlas buttonsAtlas3;
    private Skin buttonSkin3;
    private TextButton button3;
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
    private final MyGdxGame myGdxGame;
    int currentTextureIndexToRight = 0;
    int currentTextureIndexLeft = 0;

    final int pointCounterMarginRight = 400;
    final int pointCounterMarginTop = 60;

    ScreenGame(MyGdxGame myGdxGame) {
        new Music() {
            @Override
            public void play() {

            }

            @Override
            public void pause() {

            }

            @Override
            public void stop() {

            }

            @Override
            public boolean isPlaying() {
                return false;
            }

            @Override
            public void setLooping(boolean isLooping) {

            }

            @Override
            public boolean isLooping() {
                return false;
            }

            @Override
            public void setVolume(float volume) {

            }

            @Override
            public float getVolume() {
                return 0;
            }

            @Override
            public void setPan(float pan, float volume) {

            }

            @Override
            public void setPosition(float position) {

            }

            @Override
            public float getPosition() {
                return 0;
            }

            @Override
            public void dispose() {

            }

            @Override
            public void setOnCompletionListener(OnCompletionListener listener) {

            }
        }; {
            music = Gdx.audio.newMusic(Gdx.files.internal("game.mp3"));
            music.setLooping(true);
            music.setVolume(0.3f);
            music.play();
        }
        //массив текстур передвижения вправо
        Texture textureToRight1 = new Texture("R_An05_F00.png");
        Texture textureToRight2 = new Texture("R_An05_F01.png");
        Texture textureToRight3 = new Texture("R_An05_F02.png");
        Texture textureToRight4 = new Texture("R_An05_F03.png");
        Texture textureToRight5 = new Texture("R_An05_F04.png");
        Texture textureToRight6 = new Texture("R_An05_F05.png");
        Texture textureToRight7 = new Texture("R_An05_F06.png");
        Texture textureToRight8 = new Texture("R_An05_F07.png");
        Texture textureToRight9 = new Texture("R_An05_F08.png");
        Texture textureToRight10 = new Texture("R_An05_F09.png");
        Texture[] texturesToRight = new Texture[]{textureToRight1, textureToRight2, textureToRight3,textureToRight4, textureToRight5, textureToRight6,textureToRight7, textureToRight8, textureToRight9,textureToRight10};

        // массив текстур передвижения влево
        Texture textureToLeft1 = new Texture("L_An05_F00.png");
        Texture textureToLeft2 = new Texture("L_An05_F01.png");
        Texture textureToLeft3 = new Texture("L_An05_F02.png");
        Texture textureToLeft4 = new Texture("L_An05_F03.png");
        Texture textureToLeft5 = new Texture("L_An05_F04.png");
        Texture textureToLeft6 = new Texture("L_An05_F05.png");
        Texture textureToLeft7 = new Texture("L_An05_F06.png");
        Texture textureToLeft8 = new Texture("L_An05_F07.png");
        Texture textureToLeft9 = new Texture("L_An05_F08.png");
        Texture textureToLeft10 = new Texture("L_An05_F09.png");
        Texture[] texturesToLeft = new Texture[]{textureToLeft1, textureToLeft2, textureToLeft3,textureToLeft4, textureToLeft5, textureToLeft6,textureToLeft7, textureToLeft8, textureToLeft9,textureToLeft10};

        this.myGdxGame = myGdxGame;



        // текстура персонажа, заднего фона и создание счетчика
        character = new Character(100, 120, characterTexture, 0, 500, 500);
        characterTexture = new Texture("An01_F00.png");
        pointCounter = new PointCounter(SCR_WIDTH - pointCounterMarginRight, SCR_HEIGHT - pointCounterMarginTop);
        backgroundTexture = new TextureRegion(new Texture("Example.jpg"), 0, 0, 2048, 563).getTexture();



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
                character.setisStandingToLeft(false);
                isTouchDownRight = true;
                new Thread(() -> {
                    while (isTouchDownRight) {
                        character.setrunToRight(true);
                        character.runningToRight();
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

        // кнопка перендвижения влево

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
                character.setisStandingToLeft(false);
                isTouchDownLeft = true;
                new Thread(() -> {
                    while (isTouchDownLeft) {
                        character.setrunToLeft(true);
                        character.runningToLeft();
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
                character.setisStandingToLeft(true);
                character.isCharacterStandingToLeft();
            }

        });
        // кнопка добавления счетчика(enemy)

        buttonsAtlas3 = new TextureAtlas("buttons.pack");
        buttonSkin3 = new Skin();
        buttonSkin3.addRegions(buttonsAtlas3);
        font3 = new BitmapFont();
        font3.getData().scale(5f);
        font3.setColor(Color.WHITE);


        TextButton.TextButtonStyle style3 = new TextButton.TextButtonStyle();
        style3.up = buttonSkin3.getDrawable("ButtonOff");
        style3.down = buttonSkin3.getDrawable("ButtonOn");
        style3.font = font2;

        button3 = new TextButton("Object", style3);
        button3.setPosition(1000, 200);
        button3.setHeight(300);
        button3.setWidth(300);
        button3.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                character.setisStanding(false);
                character.setisStandingToLeft(false);
                character.setisAttacking(true);
                character.isCharacterAttacking();
                count += points;
                if (count == 10) {
                    points *= 2;
                }
                if (count == 100) {
                    points *= 2;
                }
                if (count == 500) {
                    points *= 2;
                }
                if (count == 1100) {
                    points *= 2;
                }
                System.out.println(count);
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                character.setcurrentTextureIndexAttacking(0);
                character.setisAttacking(false);
                character.setisStanding(true);
                character.isCharacterStanding();
            }

        });
        stage.addActor(button);
        stage.addActor(button2);
        stage.addActor(button3);


    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {

        int checkedX = character.getXToCheck();
        if (checkedX>= 1700){
            myGdxGame.setScreen(myGdxGame.shopScreen);
            character.setX(100);
        }

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
        buttonSkin3.dispose();
        buttonsAtlas3.dispose();
        font3.dispose();
        buttonSkin4.dispose();
        buttonsAtlas4.dispose();
        font4.dispose();
        pointCounter.dispose();
    }
}