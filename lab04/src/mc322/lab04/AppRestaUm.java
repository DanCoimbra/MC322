import java.util.Arrays;

public class AppRestaUm {
	// Aplicativo para jogar Resta Um a partir de um arquivo .csv contendo as jogadas.
	// O arquivo solution.csv fornece uma jogatina perfeita do Resta Um sem jogadas inválidas.
	
	public static void main (String[] args) {
		String filepath = "../../../testes/solution.csv";
		executaJogo(filepath);
	}

	public static String[] executaJogo(String filepath) {
		// Extrai a sequência de comandos a partir do .csv em filepath, na forma e.g. 'a1:b2'. 
		CSVReader csv = new CSVReader();
		csv.setDataSource(filepath);
		String[] commands = csv.requestCommands();

		// Cria um Board e exibe seu estado inicial.
		Board board = new Board();
		System.out.println("Tabuleiro inicial:");
		GameUtils.printBoard(board.toString());

		// Cria o histórico do Board neste Jogo, com um vetor em cada String é uma serialização do Board.
		String[] boardHistory = new String[commands.length + 1];
		int boardHistoryIndex = 0;
		boardHistory[boardHistoryIndex++] = board.toString();

		// Executa os comandos até que não sobre nenhum.
		while (commands.length > 0) {
			// Seleciona o primeiro comando, o elimita da lista de comandos, e o exibe na tela.
			String nextCommand = commands[0];
			commands = Arrays.copyOfRange(commands, 1, commands.length);
			System.out.println("Source: " + nextCommand.split(":")[0] + "\nTarget: " + nextCommand.split(":")[1]);

			// Converte o comando em um array de 4 inteiros e executa o comando.
			// Os inteiros correspondem aos índices das Peças no Board, que tem a forma de uma matriz de Peças.
			int[] nextCommandConverted = GameUtils.convertCommand(nextCommand);
			board.movePiece(nextCommandConverted);

			// Exibe o estado resultante do Board na tela e o armazena no Board.
			GameUtils.printBoard(board.toString());
			boardHistory[boardHistoryIndex++] = board.toString();

		}

		// Finaliza retornando o histórico do Board neste Jogo.
		return boardHistory;
	}
}