public class Piece {
	// Objeto Peça que armazena sua localização.
	int[] location;
	int player; // 0 denotes whites, 1 denotes blacks

	public Piece(int i, int j, int player) {
		location = new int[2];
		location[0] = i;
		location[1] = j;
		this.player = player;
	}

	public void move(int i, int j) {
		location[0] = i;
		location[1] = j;
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

}