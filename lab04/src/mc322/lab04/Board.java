import java.util.Arrays;

public class Board {
	static int[][] blocked = {{0,0}, {0,1}, {1,0}, {1,1}, {5,0}, {5,1}, {6,0}, {6,1}, {0,5}, {0,6}, {1,5}, {1,6}, {5,5}, {5,6}, {6,5}, {6,6}};
	Piece[][] pieces;

	public Board() {
		this.pieces = new Piece[7][7];
		boolean outOfBounds;
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				outOfBounds = GameUtils.hasLocation(i, j, blocked);
				if (outOfBounds || (i == 3 && j == 3))
					this.pieces[i][j] = null;
				else
					this.pieces[i][j] = new Piece(i, j);
			}
		}
	}

	public String getContent(int i, int j) {
		boolean outOfBounds = GameUtils.hasLocation(i, j, blocked);
		if (outOfBounds || i < 0 || i > 6 || j < 0 || j > 6)
			return " ";
		else if (this.pieces[i][j] == null)
			return "-";
		else
			return "P";
	}

	public String toString() {
		String strRepr = "";
		for (int col = 6; col >= 0; col--) {
			for (int row = 0; row < 7; row++)
				strRepr += this.getContent(row, col);			
			strRepr += '\n';
		}
		return strRepr;
	}

	public void movePiece(int[] command) {
		int[] src = Arrays.copyOfRange(command, 0, 2);
		int[] dst = Arrays.copyOfRange(command, 2, 4);
		int[] med = GameUtils.intermediaryLocation(src, dst);
		boolean validMove = GameUtils.checkMove(src, med, dst, this);
		if (validMove) {
			this.pieces[src[0]][src[1]].move(dst[0], dst[1]);
			this.pieces[dst[0]][dst[1]] = this.pieces[src[0]][src[1]];
			this.pieces[src[0]][src[1]] = null;
			this.pieces[med[0]][med[1]] = null;
		}
	}
}