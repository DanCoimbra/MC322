public class Piece {
	// Objeto Peça que armazena sua localização.
	int[] location;

	public Piece(int i, int j) {
		location = new int[2];
		location[0] = i;
		location[1] = j;
	}

	public void move(int i, int j) {
		location[0] = i;
		location[1] = j;
	}

}