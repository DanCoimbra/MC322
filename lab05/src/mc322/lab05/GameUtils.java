import java.util.Arrays;

public class GameUtils {
	// Utilitários para um jogo de Resta Um.
	
	public static boolean hasLocation(int i, int j, int[][] array2D) {
		// Verifica se um array de dois inteiros está contido em um array de arrays.
		int[] target = {i, j};
		for (int[] loc : array2D)
			if (Arrays.equals(loc, target))
				return true;
		return false;
	}

	public static int[] convertCommand(String command) {
		// Converte um comando textual em uma sequência de números.
		String[] commandSplit = command.split(":");
		char srcRow = commandSplit[0].charAt(0);
		String srcCol = commandSplit[0].substring(1);
		char dstRow = commandSplit[1].charAt(0);
		String dstCol = commandSplit[1].substring(1);

		int srcRowIndex = ((int) srcRow) - 97;
		int srcColIndex = Integer.parseInt(srcCol) - 1;
		int dstRowIndex = ((int) dstRow) - 97;
		int dstColIndex = Integer.parseInt(dstCol) - 1;
		int[] returnValue = {srcRowIndex, srcColIndex, dstRowIndex, dstColIndex};
		return returnValue;
	}

	public static void printBoard(String strBoard) {
		// Imprime o tabuleiro na tela com a formatação adequada.
		char c = '\0';
		int row = 7;
		System.out.print(row-- + " ");
		for (int i = 0; i < strBoard.length(); i++) {
			c = strBoard.charAt(i);
			System.out.print(c);
			if (c != '\n')
				System.out.print(" ");
			if (c == '\n' && row >= 1)
				System.out.print(row-- + " ");
		}
		System.out.print("  a b c d e f g\n\n");
	}

}