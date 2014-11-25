package eu.veldsoft.hungarian.rings;

class Rings {

	private int rings[][] = new int[2][18];
	private int coordiantes[][][] = new int[2][18][2];

	private int width;
	private int height;
	private int pdiam;
	private int rdiam;

	private int moves;

	public Rings() {
		this(0, 0);
	}

	public Rings(int width, int height) {
		init(width, height);
	}

	public void init(int width, int height) {
		this.width = width;
		this.height = height;

		pdiam = (int) (Math.min(width, height) * 0.08);
		rdiam = (int) (2.9 * pdiam);

		for (double i = 0, alpha = 38 * Math.PI / 19; i < 18; i++, alpha--) {
			coordiantes[0][(int) i][1] = (int) (Math.min(width, height) / 2.0 + rdiam
					* Math.cos(alpha * 2 * Math.PI / 19));
			coordiantes[0][(int) i][0] = (int) (Math.min(width, height) / 2.0
					- (rdiam * Math.cos(10 * Math.PI / 38)) + rdiam
					* Math.sin(alpha * 2 * Math.PI / 19));
		}

		for (double i = 0, alpha = -19.75 * Math.PI / 19; i < 18; i++, alpha--) {
			coordiantes[1][(int) i][1] = (int) (Math.min(width, height) / 2.0 + rdiam
					* Math.cos(alpha * 2 * Math.PI / 19));
			coordiantes[1][(int) i][0] = (int) (Math.min(width, height) / 2.0
					+ (rdiam * Math.cos(10 * Math.PI / 38)) + rdiam
					* Math.sin(alpha * 2 * Math.PI / 19));
		}

		// TODO Use constants for token colors.
		for (int i = 0; i < 9; i++)
			rings[0][i] = 1;
		for (int i = 9; i < 18; i++)
			rings[0][i] = 2;
		for (int i = 0; i < 9; i++)
			rings[1][i] = 3;
		for (int i = 9; i < 18; i++)
			rings[1][i] = 4;
		moves = 0;

		int inten;
		double sigma = 0.5;
		double miu = pdiam / 3.0;
		for (int j = 0; j < pdiam; j++)
			for (int i = 0; i < pdiam; i++)
				if (Math.pow(pdiam / 2 - i, 2) + Math.pow(pdiam / 2 - j, 2) < Math
						.pow(pdiam / 2, 2)) {
					inten = (int) (128.0 - 255.0
							* (Math.pow(pdiam / 3 - i, 2) + Math.pow(pdiam / 3
									- j, 2)) / Math.pow(pdiam + 1, 2));

					// SetPixel(shadhDC, i, j, RGB(inten, inten, inten));
				} else {
					// SetPixel(shadhDC, i, j, RGB(0, 0, 0));
				}
	}

	public void cwa() {
		int temp = rings[0][0];
		for (int i = 0; i < 17; i++)
			rings[0][i] = rings[0][i + 1];
		rings[0][17] = rings[1][4];
		rings[1][4] = temp;
	}

	public void ccwa() {
		int temp = rings[0][17];
		for (int i = 17; i > 0; i--)
			rings[0][i] = rings[0][i - 1];
		rings[0][0] = rings[1][4];
		rings[1][4] = temp;
	}

	public void cwb() {
		int temp = rings[1][0];
		for (int i = 0; i < 17; i++)
			rings[1][i] = rings[1][i + 1];
		rings[1][17] = rings[0][4];
		rings[0][4] = temp;
	}

	public void ccwb() {
		int temp = rings[1][17];
		for (int i = 17; i > 0; i--)
			rings[1][i] = rings[1][i - 1];
		rings[1][0] = rings[0][4];
		rings[0][4] = temp;
	}

	public void shuffle() {
		// TODO Find better estimation for number of moves during shuffling.
		for (int i = 0; i < 10000; i++) {
			switch (Util.PRNG.nextInt(4)) {
			case 0:
				cwa();
				break;
			case 1:
				ccwa();
				break;
			case 2:
				cwb();
				break;
			case 3:
				ccwb();
				break;
			}
		}

		moves = 0;
	}

	public void reset() {
		init(width, height);
	}

	public boolean eval(String commands) {
		for (int i = -1, rep = 0; i < commands.length();)
			if (commands.charAt(++i) == '+') {
				if ('0' <= commands.charAt(++i) && commands.charAt(i) <= '9')
					rep = commands.charAt(i++) - '0';
				else
					rep = 1;

				if (commands.charAt(i) == 'A')
					for (int j = 0; j < rep; j++)
						cwa();
				else if (commands.charAt(i) == 'B')
					for (int j = 0; j < rep; j++)
						cwb();
				else
					return (false);
			} else if (commands.charAt(i) == '-') {
				if ('0' <= commands.charAt(++i) && commands.charAt(i) <= '9')
					rep = commands.charAt(i++) - '0';
				else
					rep = 1;

				if (commands.charAt(i) == 'A')
					for (int j = 0; j < rep; j++)
						ccwa();
				else if (commands.charAt(i) == 'B')
					for (int j = 0; j < rep; j++)
						ccwb();
				else
					return (false);
			} else {
				return false;
			}

		return true;
	}

	public boolean action(int x, int y) {
		boolean done = false;

		for (int j = 0; j < 18; j++)
			switch (j) {
			case 2:
			case 3:
			case 12:
			case 13:
			case 14:
			case 15:
			case 16:
			case 17:
				if ((Math.pow(x - coordiantes[0][j][0], 2.0) + Math.pow(y
						- coordiantes[0][j][1], 2.0)) < Math.pow(pdiam / 2.0,
						2.0)) {
					cwa();
					done = true;
				}
				break;
			case 0:
			case 1:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
				if ((Math.pow(x - coordiantes[0][j][0], 2.0) + Math.pow(y
						- coordiantes[0][j][1], 2.0)) < Math.pow(pdiam / 2.0,
						2.0)) {
					ccwa();
					done = true;
				}
				break;
			}

		for (int j = 0; j < 18; j++)
			switch (j) {
			case 0:
			case 1:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
				if ((Math.pow(x - coordiantes[1][j][0], 2.0) + Math.pow(y
						- coordiantes[1][j][1], 2.0)) < Math.pow(pdiam / 2.0,
						2.0)) {
					cwb();
					done = true;
				}
				break;
			case 2:
			case 3:
			case 12:
			case 13:
			case 14:
			case 15:
			case 16:
			case 17:
				if ((Math.pow(x - coordiantes[1][j][0], 2.0) + Math.pow(y
						- coordiantes[1][j][1], 2.0)) < Math.pow(pdiam / 2.0,
						2.0)) {
					ccwb();
					done = true;
				}
				break;
			}

		moves++;

		return done;
	}

	public boolean action(char symbol) {
		String sequ = "";

		if (symbol == 'N')
			reset();
		else if (symbol == 'S')
			shuffle();
		else if (symbol == 'Q')
			;
		else if (symbol == '+' || symbol == '-')
			if (sequ.length() == 0 || sequ.charAt(sequ.length() - 1) == 'A'
					|| sequ.charAt(sequ.length() - 1) == 'B') {
				sequ += " ";
				sequ += symbol;
			} else
				return (false);
		else if ('0' <= symbol && symbol <= '9')
			if (sequ.charAt(sequ.length() - 1) == '+'
					|| sequ.charAt(sequ.length() - 1) == '-') {
				sequ += " ";
				sequ += symbol;
			} else
				return (false);
		else if (symbol == 'A' || symbol == 'B')
			if (('0' <= sequ.charAt(sequ.length() - 1) && sequ.charAt(sequ
					.length() - 1) <= '9')
					|| sequ.charAt(sequ.length() - 1) == '+'
					|| sequ.charAt(sequ.length() - 1) == '-') {
				sequ += " ";
				sequ += symbol;
			} else
				return (false);
		else if ((symbol == '\n' || symbol == '\r')
				&& (sequ.charAt(sequ.length() - 1) == 'A' || sequ.charAt(sequ
						.length() - 1) == 'B')) {
			eval(sequ);
			sequ = "";
		} else
			return (false);

		return true;
	}

	public boolean isDone() {
		for (int i = 0; i < 17; i++) {
			if (i == 8) {
				continue;
			} else if (rings[0][i] != rings[0][i + 1]) {
				return false;
			} else if (rings[1][i] != rings[1][i + 1]) {
				return false;
			}
		}

		return true;
	}

	public int[] getState() {
		int[] result = new int[rings[0].length + rings[1].length];

		for (int a = 0, j = 0; a < 2; a++) {
			for (int i = 0; i < 18; i++, j++) {
				result[j] = rings[a][i];
			}
		}

		return result;
	}
}
