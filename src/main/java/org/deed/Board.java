import java.util.*;

public class Board implements Cloneable {
	public static final int PAWN = 1;
	public static final int KNIGHT = 2;
	public static final int BISHOP = 3;
	public static final int ROOK = 4;
	public static final int QUEEN = 5;
	public static final int KING = 6;
	public static final int WHITE = 1;
	public static final int BLACK = -1;
	public Boolean[] canCastleK = {true, true};
	public Boolean[] canCastleQ = {true, true};
	public int[] epsquare = {-1, -1};
	public int[][] board;
	public int side;
	public int turn;
	public Board() {
		turn = 1;
		side = 1;
		board = new int[8][8];
	}
	public void printBoard() {
		System.out.println("   A    B    C    D    E    F    G    H");
		System.out.println("  +----+----+----+----+----+----+----+----+");
		for (int x = 0; x < board.length; x++) {
			System.out.print((x + 1) + " ");
			for (int y = board[x].length - 1; y >= 0; y--) {
				System.out.print("| ");
				if (board[x][y] < 0) {
					System.out.print("B ");
				} else if (board[x][y] > 0) {
					System.out.print("W ");
				} else {
					System.out.print("  ");
				}
				System.out.print(" ");
			}
			System.out.println("| ");
			System.out.print("  ");
			for (int y = board[x].length - 1; y >= 0; y--) {
				System.out.print("|  ");
				if (board[x][y] == 0) {
					System.out.print("  ");
				} else if (Math.abs(board[x][y]) == PAWN) {
					System.out.print("P ");
				} else if (Math.abs(board[x][y]) == KNIGHT) {
					System.out.print("N ");
				} else if (Math.abs(board[x][y]) == BISHOP) {
					System.out.print("B ");
				} else if (Math.abs(board[x][y]) == ROOK) {
					System.out.print("R ");
				} else if (Math.abs(board[x][y]) == QUEEN) {
					System.out.print("Q ");
				} else if (Math.abs(board[x][y]) == KING) {
					System.out.print("K ");
				}
			}
			System.out.println("|");
			System.out.println("  +----+----+----+----+----+----+----+----+");
		}
	}
	public ArrayList<int[]> findMoves() {
		int finalrank = 7;
		if (side == BLACK) {
			finalrank = 0;
		}
		ArrayList<int[]> moves = new ArrayList<int[]>(0);
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				if (board[x][y] == side * PAWN) {
					if (x == epsquare[0] && (y == epsquare[1] + 1 || y == epsquare[1] - 1)) {
						int[] move = {x, y, x + side, epsquare[1], 0};
						moves.add(move);
					}
					if (board[x + side][y] == 0) {
						if (x + side == finalrank) {
							for (int i = KNIGHT; i <= QUEEN; i++) {
								int[] move = {x, y, x + side, y, i};
								moves.add(move);
							}
						} else {
							int[] move = {x, y, x + side, y, 0};
							moves.add(move);
						}
					}
					if (y < 7 && board[x + side][y + 1] * side < 0) {
						if (x + side == finalrank) {
							for (int i = KNIGHT; i <= QUEEN; i++) {
								int[] move = {x, y, x + side, y + 1, i};
								moves.add(move);
							}
						} else {
							int[] move = {x, y, x + side, y + 1, 0};
							moves.add(move);
						}
					}
					if (y > 0 && board[x + side][y - 1] * side < 0) {
						if (x + side == finalrank) {
							for (int i = KNIGHT; i <= QUEEN; i++) {
								int[] move = {x, y, x + side, y - 1, i};
								moves.add(move);
							}
						} else {
							int[] move = {x, y, x + side, y - 1, 0};
							moves.add(move);
						}
					}
					int startingrank = 1;
					if (side == BLACK) {
						startingrank = 6;
					}
					if (x == startingrank && board[x + side][y] == 0 && board[x + side * 2][y] == 0) {
						int[] move = {x, y, x + side * 2, y, 0};
						moves.add(move);
					}
				}
				if (board[x][y] == side * KNIGHT) {
					if (x + 2 <= 7 && y + 1 <= 7 && board[x + 2][y + 1] * side <= 0) {
						int[] move = {x, y, x + 2, y + 1, 0};
						moves.add(move);
					}
					if (x - 2 >= 0 && y + 1 <= 7 && board[x - 2][y + 1] * side <= 0) {
						int[] move = {x, y, x - 2, y + 1, 0};
						moves.add(move);
					}
					if (x + 2 <= 7 && y - 1 >= 0 && board[x + 2][y - 1] * side <= 0) {
						int[] move = {x, y, x + 2, y - 1, 0};
						moves.add(move);
					}
					if (x - 2 >= 0 && y - 1 >= 0 && board[x - 2][y - 1] * side <= 0) {
						int[] move = {x, y, x - 2, y - 1, 0};
						moves.add(move);
					}
					if (x + 1 <= 7 && y + 2 <= 7 && board[x + 1][y + 2] * side <= 0) {
						int[] move = {x, y, x + 1, y + 2, 0};
						moves.add(move);
					}
					if (x - 1 >= 0 && y + 2 <= 7 && board[x - 1][y + 2] * side <= 0) {
						int[] move = {x, y, x - 1, y + 2, 0};
						moves.add(move);
					}
					if (x + 1 <= 7 && y - 2 >= 0 && board[x + 1][y - 2] * side <= 0) {
						int[] move = {x, y, x + 1, y - 2, 0};
						moves.add(move);
					}
					if (x - 1 >= 0 && y - 2 >= 0 && board[x - 1][y - 2] * side <= 0) {
						int[] move = {x, y, x - 1, y - 2, 0};
						moves.add(move);
					}
				}
				if (board[x][y] == side * BISHOP || board[x][y] == side * QUEEN) {
					for (int i = 1; x + i <= 7 && y + i <= 7 && board[x + i][y + i] * side <= 0; i++) {
						int[] move = {x, y, x + i, y + i, 0};
						moves.add(move);
						if (board[x + i][y + i] != 0) {
							break;
						}
					}
					for (int i = 1; x + i <= 7 && y - i >= 0 && board[x + i][y - i] * side <= 0; i++) {
						int[] move = {x, y, x + i, y - i, 0};
						moves.add(move);
						if (board[x + i][y - i] != 0) {
							break;
						}
					}
					for (int i = 1; x - i >= 0 && y + i <= 7 && board[x - i][y + i] * side <= 0; i++) {
						int[] move = {x, y, x - i, y + i, 0};
						moves.add(move);
						if (board[x - i][y + i] != 0) {
							break;
						}
					}
					for (int i = 1; x - i >= 0 && y - i >= 0 && board[x - i][y - i] * side <= 0; i++) {
						int[] move = {x, y, x - i, y - i, 0};
						moves.add(move);
						if (board[x - i][y - i] != 0) {
							break;
						}
					}
				}
				if (board[x][y] == side * ROOK || board[x][y] == side * QUEEN) {
					for (int i = 1; x + i <= 7 && board[x + i][y] * side <= 0; i++) {
						int[] move = {x, y, x + i, y, 0};
						moves.add(move);
						if (board[x + i][y] != 0) {
							break;
						}
					}
					for (int i = 1; x - i >= 0 && board[x - i][y] * side <= 0; i++) {
						int[] move = {x, y, x - i, y, 0};
						moves.add(move);
						if (board[x - i][y] != 0) {
							break;
						}
					}
					for (int i = 1; y + i <= 7 && board[x][y + i] * side <= 0; i++) {
						int[] move = {x, y, x, y + i, 0};
						moves.add(move);
						if (board[x][y + i] != 0) {
							break;
						}
					}
					for (int i = 1; y - i >= 0 && board[x][y - i] * side <= 0; i++) {
						int[] move = {x, y, x, y - i, 0};
						moves.add(move);
						if (board[x][y - i] != 0) {
							break;
						}
					}
				}
				if (board[x][y] == side * KING) {
					if (x + 1 <= 7 && board[x + 1][y] * side <= 0) {
						int[] move = {x, y, x + 1, y, 0};
						moves.add(move);
					}
					if (x + 1 <= 7 && y + 1 <= 7 && board[x + 1][y + 1] * side <= 0) {
						int[] move = {x, y, x + 1, y + 1, 0};
						moves.add(move);
					}
					if (y + 1 <= 7 && board[x][y + 1] * side <= 0) {
						int[] move = {x, y, x, y + 1, 0};
						moves.add(move);
					}
					if (x - 1 >= 0 && y + 1 <= 7 && board[x - 1][y + 1] * side <= 0) {
						int[] move = {x, y, x - 1, y + 1, 0};
						moves.add(move);
					}
					if (x - 1 >= 0 && board[x - 1][y] * side <= 0) {
						int[] move = {x, y, x - 1, y, 0};
						moves.add(move);
					}
					if (x - 1 >= 0 && y - 1 >= 0 && board[x - 1][y - 1] * side <= 0) {
						int[] move = {x, y, x - 1, y - 1, 0};
						moves.add(move);
					}
					if (y - 1 >= 0 && board[x][y - 1] * side <= 0) {
						int[] move = {x, y, x, y - 1, 0};
						moves.add(move);
					}
					if (x + 1 <= 7 && y - 1 >= 0 && board[x + 1][y - 1] * side <= 0) {
						int[] move = {x, y, x + 1, y - 1, 0};
						moves.add(move);
					}
				}
			}
		}
		return moves;
	}
	public Boolean checkForCheck(int sideToCheck) { // Checks if sideToCheck is in check
		int[] kingSquare = {0, 0};
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board.length; y++) {
				if (board[x][y] == sideToCheck * KING) {
					kingSquare[0] = x;
					kingSquare[1] = y;
					break;
				}
			}
		}
		Board ifOpponent = new Board();
		ifOpponent.board = board;
		ifOpponent.side = -sideToCheck;
		for (int[] move : ifOpponent.findMoves()) {
			if (move[2] == kingSquare[0] && move[3] == kingSquare[1]) {
				return true;
			}
		}
		return false;
	}
	public ArrayList<int[]> findLegalMoves() {
		ArrayList<int[]> moves = findMoves();
		int backrank = 0;
		if (side == BLACK) {
			backrank = 7;
		}
		if (canCastleK[(1 + side) / 2] && board[backrank][1] == 0 && board[backrank][2] == 0 && !(calcNewPosition(new int[] {backrank, 3, backrank, 2, 0}).checkForCheck(side) || calcNewPosition(new int[] {backrank, 3, backrank, 1, 0}).checkForCheck(side))) {
			int[] move = {0, 0, 0, 0, 1};
			moves.add(move);
		}
		if (canCastleQ[(1 + side) / 2] && board[backrank][6] == 0 && board[backrank][5] == 0 && board[backrank][4] == 0 && !(calcNewPosition(new int[] {backrank, 3, backrank, 4, 0}).checkForCheck(side) || calcNewPosition(new int[] {backrank, 3, backrank, 5, 0}).checkForCheck(side) || calcNewPosition(new int[] {backrank, 3, backrank, 6, 0}).checkForCheck(side))) {
			int[] move = {1, 0, 0, 0, 1};
			moves.add(move);
		}
		ArrayList<int[]> updated = new ArrayList<int[]>();
		for (int[] move : moves) {
			if (!calcNewPosition(move).checkForCheck(side)) {
				updated.add(move);
			}
		}
		return updated;
	}
	public Board calcNewPosition(int[] move) {
		int backrank = 7;
		if (side == WHITE) {
			backrank = 0;
		}
		Board newPosition = new Board();
		for (int i = 0; i < newPosition.board.length; i++) {
			newPosition.board[i] = Arrays.copyOf(board[i], board[i].length);
		}
		newPosition.canCastleK = Arrays.copyOf(canCastleK, 2);
		newPosition.canCastleQ = Arrays.copyOf(canCastleQ, 2);
		if (move[4] == 1) {
			if (move[0] == 0) {
				newPosition.canCastleK[(1 + side) / 2] = false;
				newPosition.board[backrank][1] = side * KING;
				newPosition.board[backrank][2] = side * ROOK;
				newPosition.board[backrank][3] = 0;
				newPosition.board[backrank][0] = 0;
			} else if (move[0] == 1) {
				newPosition.canCastleQ[(1 + side) / 2] = false;
				newPosition.board[backrank][5] = side * KING;
				newPosition.board[backrank][4] = side * ROOK;
				newPosition.board[backrank][3] = 0;
				newPosition.board[backrank][7] = 0;
			}
		} else if (move[4] != 0) {
			newPosition.board[move[2]][move[3]] = side * move[4];
			newPosition.board[move[0]][move[1]] = 0;
		} else {
			if (board[move[0]][move[1]] == side * KING) {
				newPosition.canCastleK[(1 + side) / 2] = false;
				newPosition.canCastleQ[(1 + side) / 2] = false;
			}
			if (board[move[0]][move[1]] == side * ROOK && move[1] == 0) {
				newPosition.canCastleK[(1 + side) / 2] = false;
			}
			if (board[move[0]][move[1]] == side * ROOK && move[1] == 7) {
				newPosition.canCastleQ[(1 + side) / 2] = false;
			}
			if (board[move[0]][move[1]] == side * PAWN && move[2] == move[0] + 2 * side) {
				newPosition.epsquare = new int[] {move[2], move[3]};
			}
			if (board[move[0]][move[1]] == side * PAWN && move[2] == epsquare[0] + side && move[3] == epsquare[1]) {
				newPosition.board[epsquare[0]][epsquare[1]] = 0;
			}
			newPosition.board[move[2]][move[3]] = newPosition.board[move[0]][move[1]];
			newPosition.board[move[0]][move[1]] = 0;
		}
		newPosition.side = -side;
		newPosition.turn = turn + 1;
		return newPosition;
	}
}