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

	public static boolean validateSource(int[] src) {
		// Averigua se está-se tentando mover uma Peça genuína.
		boolean srcBoundsRow = (src[0] >= 0 && src[0] <= 6);
		boolean srcBoundsCol = (src[1] >= 0 && src[1] <= 6);
		boolean srcNotBlocked = !GameUtils.hasLocation(src[0], src[1], Board.blocked);
		return srcBoundsRow && srcBoundsCol && srcNotBlocked;
	}

	public static boolean validateDestination(int[] src, int[] dst) {
		// Averigua se está-se tentando mover uma Peça para uma posição válida.
		boolean dstBoundsRow = (dst[0] >= 0 && dst[0] <= 6);
		boolean dstBoundsCol = (dst[1] >= 0 && dst[1] <= 6);
		boolean dstNotBlocked = !GameUtils.hasLocation(dst[0], dst[1], Board.blocked);
		boolean dstPartialValid = (dstBoundsRow & dstBoundsCol & dstNotBlocked);
		if (dstPartialValid) {
			// Two-step column-wise movement.
			if (src[0] == dst[0] && (src[1] == dst[1] - 2 || src[1] == dst[1] + 2))
				return true;
			// Two-step row-wise movement.
			else if (src[1] == dst[1] && (src[0] == dst[0] - 2 || src[0] == dst[0] + 2))
				return true;
			// Movimento inválido. Either diagonal movement or not two-step movement.
			else
				return false;
		} else
			return false;
	}


	public static boolean checkMove(int[] src, int[] med, int[] dst, Board board) {
		// Averigua se uma jogada é válida.
		boolean srcValid = GameUtils.validateSource(dst);
		boolean dstValid = GameUtils.validateDestination(src, dst);
		if (srcValid && dstValid) {
			boolean eatsPiece = (board.getContent(med[0], med[1]) == "P");
			boolean vacantDst = (board.getContent(dst[0], dst[1]) == "-");
			if (eatsPiece && vacantDst)
				return true;
		}
		return false;
	}

	public static int[] intermediaryLocation(int[] src, int[] dst) {
		// Retorna a posição intermediária entre duas posições do tabuleiro.
		// Esta função é chamada apenas para pares (src, dst) válidos.
		int[] med = new int[2];
		if (src[0] == dst[0]) {
			med[0] = src[0];
			if (src[1] > dst[1]) // Leftward movement.
				med[1] = src[1] - 1;
			else // Rightward movement.
				med[1] = src[1] + 1;
		} else {
			med[1] = src[1];
			if (src[0] > dst[0]) // Downward movement.
				med[0] = src[0] - 1;
			else // Upward movement.
				med[0] = src[0] + 1;
		}
		return med;
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