import DesignPatterns.Builder.Car;
import DesignPatterns.Factory.Vehicle;
import DesignPatterns.Factory.VehicleFactory;
import DesignPatterns.Singleton.Logger;
import TicTacToe.model.Board;
import TicTacToe.model.Player;
import TicTacToe.service.Game;
import TicTacToe.strategy.RowColDiagonalWinning;

import java.util.List;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello, World!");


        //Call factory
        Vehicle vehicle = VehicleFactory.getVehicle("Bus");
        if (vehicle != null) {
            vehicle.start();
            sleep(2000);
            vehicle.stop();
        }

        //Call singleton
        Logger logger = Logger.getLogger();
        logger.log("siiiiiiiiiu");

        //Call builder
        Car.CarBuilder carBuilder = new Car.CarBuilder();

        Car obj = carBuilder.
                setEngine("testEngine12").
                setColor("Red").
                setWheels(6).
                build();
        System.out.println(obj.toString());

        Player p1 = new Player("A", 'X');
        Player p2 = new Player("B", 'O');

        Game.GameBuilder gameBuilder = new Game.GameBuilder();
        Game game = gameBuilder.setBoard(
                new Board.BoardBuilder().setBoardSize(3).build()
        ).setPlayerList(List.of(p1,p2)).setWinningStrategy(
                new RowColDiagonalWinning()
        ).build();

        game.makeMove(0, 0); // A
        game.makeMove(1, 0); // B
        game.makeMove(0, 1); // A
        game.makeMove(1, 1); // B
        game.makeMove(0, 2); // A
        game.undo();
        game.makeMove(0, 2); // A
    }
}