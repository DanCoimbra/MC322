import java.util.Arrays;

public class Board {
	// Tabuleiro do jogo Resta Um, contendo uma matriz 7x7 de Peças.
	// A variável estática 'blocked' corresponde aos espaços do tabuleiro que não possuem buraco para inserir uma Peça.
	static int[][] blocked = {{0,0}, {0,1}, {1,0}, {1,1}, {5,0}, {5,1}, {6,0}, {6,1}, {0,5}, {0,6}, {1,5}, {1,6}, {5,5}, {5,6}, {6,5}, {6,6}};
	Piece[][] pieces;

	public Board() {
		// Cria a matriz de Peças e insere novas Peças nos espaços válidos.
		this.pieces = new Piece[7][7];
		boolean outOfBounds;
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				// Peças não serão inseridas no meio e onde não houver buraco.
				outOfBounds = GameUtils.hasLocation(i, j, blocked);
				if (outOfBounds || (i == 3 && j == 3))
					this.pieces[i][j] = null;
				else
					this.pieces[i][j] = new Piece(i, j);
			}
		}
	}

	public String getContent(int i, int j) {
		// Retorna se o espaço é inválido, vazio, ou contém uma Peça.
		boolean outOfBounds = GameUtils.hasLocation(i, j, blocked);
		if (outOfBounds || i < 0 || i > 6 || j < 0 || j > 6)
			return " ";
		else if (this.pieces[i][j] == null)
			return "-";
		else
			return "P";
	}

	public String toString() {
		// Serializa o Board.
		String strRepr = "";
		for (int col = 6; col >= 0; col--) {
			for (int row = 0; row < 7; row++)
				strRepr += this.getContent(row, col);			
			strRepr += '\n';
		}
		return strRepr;
	}

	public void movePiece(int[] command) {
		// Altera o tabuleiro para executar um comando válido. Comandos inválidos não produzem nada.
		int[] src = Arrays.copyOfRange(command, 0, 2);
		int[] dst = Arrays.copyOfRange(command, 2, 4);
		int[] med = GameUtils.intermediaryLocation(src, dst);
		// Avalia se o comando é válido.
		boolean validMove = GameUtils.checkMove(src, med, dst, this);
		if (validMove) {
			this.pieces[src[0]][src[1]].move(dst[0], dst[1]);
			this.pieces[dst[0]][dst[1]] = this.pieces[src[0]][src[1]];
			this.pieces[src[0]][src[1]] = null;
			this.pieces[med[0]][med[1]] = null;
		}
	}
}