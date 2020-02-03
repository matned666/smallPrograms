package ticTacToe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTest {


        TicTacToe game = new TicTacToe();

        @Test
        public void execute() throws Throwable{

            game.action(0,0);

            assertThrows(IllegalStateException.class, new Executable() {
                @Override
                public void execute() throws Throwable {
                    game.action(0,0);
                }
            });

        }

        @Test
        public void playerXCanWinHorizontally(){
            game.action(0,0);
            game.action(0,1);
            game.action(1,0);
            game.action(1,1);
            game.action(2,0);

            GameResult result = game.checkResult();

            assertEquals(GameResult.PLAYER_X_WIN, result);

        }

        @Test
        public void playerXCanWinVertically(){
            game.action(1,0);
            game.action(0,1);
            game.action(1,1);
            game.action(0,0);
            game.action(1,2);

            GameResult result = game.checkResult();

            assertEquals(GameResult.PLAYER_X_WIN, result);

        }

        @Test
        public void playerXCanWinAslantNormal(){
            game.action(0,0);
            game.action(0,2);
            game.action(1,1);
            game.action(0,1);
            game.action(2,2);

            GameResult result = game.checkResult();

            assertEquals(GameResult.PLAYER_X_WIN, result);

        }
        @Test
        public void playerXCanWinAslantRevert(){
            game.action(0,2);
            game.action(0,1);
            game.action(1,1);
            game.action(2,2);
            game.action(2,0);

            GameResult result = game.checkResult();

            assertEquals(GameResult.PLAYER_X_WIN, result);

        }
        @Test
        public void playerOCanWinHorizontally(){
            game.action(0,1);
            game.action(1,0);
            game.action(0,2);
            game.action(1,2);
            game.action(2,2);
            game.action(1,1);

            GameResult result = game.checkResult();

            assertEquals(GameResult.PLAYER_O_WIN, result);

        }

        @Test
        public void playerOCanWinVertically(){
            game.action(0,0);
            game.action(0,1);
            game.action(1,0);
            game.action(1,1);
            game.action(1,2);
            game.action(2,1);

            GameResult result = game.checkResult();

            assertEquals(GameResult.PLAYER_O_WIN, result);

        }

        @Test
        public void playerOCanWinAslantNormal(){
            game.action(0,1);
            game.action(0,0);
            game.action(0,2);
            game.action(1,1);
            game.action(2,1);
            game.action(2,2);

            GameResult result = game.checkResult();

            assertEquals(GameResult.PLAYER_O_WIN, result);

        }
        @Test
        public void playerOCanWinAslantRevert(){
            game.action(2,1);
            game.action(0,2);
            game.action(0,1);
            game.action(1,1);
            game.action(2,2);
            game.action(2,0);

            GameResult result = game.checkResult();

            assertEquals(GameResult.PLAYER_O_WIN, result);

        }
        @Test
        public void isDraw(){
            game.action(2,0);//x
            game.action(2,1);//o
            game.action(2,2);//x
            game.action(1,0);//o
            game.action(1,2);//x
            game.action(1,1);//0
            game.action(0,0);//x
            game.action(0,2);//o
            game.action(0,1);//x


            GameResult result = game.checkResult();

            assertEquals(GameResult.DRAW, result);

        }

    }