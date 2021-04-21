import java.util.Arrays;

public class AppRestaUm {
	
	public static void main (String[] args) {
		String filepath = "../../../testes/solution.csv";
		executaJogo(filepath);
	}

	public static String[] executaJogo(String filepath) {
		CSVReader csv = new CSVReader();
		csv.setDataSource(filepath);
		String[] commands = csv.requestCommands();

		Board board = new Board();
		String[] boardHistory = new String[commands.length + 1];
		int boardHistoryIndex = 0;
		System.out.println("Tabuleiro inicial:\n" + board.toString());
		boardHistory[boardHistoryIndex++] = board.toString();

		while (commands.length > 0) {
			String nextCommand = commands[0];
			commands = Arrays.copyOfRange(commands, 1, commands.length);
			System.out.println("Source: " + nextCommand.split(":")[0] + "\nTarget: " + nextCommand.split(":")[1]);

			int[] nextCommandConverted = GameUtils.convertCommand(nextCommand);
			board.movePiece(nextCommandConverted);

			System.out.println(board.toString());
			boardHistory[boardHistoryIndex++] = board.toString();

		}
		return boardHistory;
	}
}