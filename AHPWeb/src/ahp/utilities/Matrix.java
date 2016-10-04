package ahp.utilities;

public class Matrix {
	private float[][] data;
	private int numOfRows, numOfCols;

	public Matrix() {
		super();
	}

	public Matrix(float[][] data, int numOfRows, int numOfCols) {
		super();
		this.data = data;
		this.numOfRows = numOfRows;
		this.numOfCols = numOfCols;
	}

	public float[][] getData() {
		return this.data;
	}

	public void setData(float[][] data) {
		this.data = data;
	}

	public int getNumOfRows() {
		return numOfRows;
	}

	public void setNumOfRows(int numOfRows) {
		this.numOfRows = numOfRows;
	}

	public int getNumOfCols() {
		return numOfCols;
	}

	public void setNumOfCols(int numOfCols) {
		this.numOfCols = numOfCols;
	}

	public Matrix eigenMatrix() {
		float[][] data = new float[this.numOfRows][1];
		float[] sumOfCol = new float[this.numOfRows];
		for (int i = 0; i < this.numOfCols; i++) {
			for (int j = 0; j < this.numOfRows; j++) {
				sumOfCol[i] += this.data[j][i];
			}
		}
		for (int i = 0; i < this.numOfRows; i++) {
			float temp = 0.0f;
			for (int j = 0; j < this.numOfCols; j++) {
				temp += this.data[i][j] / sumOfCol[j];
			}
			data[i][0] = temp / this.numOfCols;
		}
		return new Matrix(data, this.numOfRows, 1);
	}

	public float[] eigenVector() {
		float[] eigenVector = new float[this.numOfRows];
		float[] sumOfCol = new float[this.numOfRows];
		for (int i = 0; i < this.numOfCols; i++) {
			for (int j = 0; j < this.numOfRows; j++) {
				sumOfCol[i] += this.data[j][i];
			}
		}
		for (int i = 0; i < this.numOfRows; i++) {
			float temp = 0.0f;
			for (int j = 0; j < this.numOfCols; j++) {
				temp += this.data[i][j] / sumOfCol[j];
			}
			eigenVector[i] = temp / this.numOfCols;
		}
		return eigenVector;
	}

	public static Matrix mutiply(Matrix m1, Matrix m2) {
		if (m1.getNumOfCols() != m2.getNumOfRows()) {
			return null;
		} else {
			Matrix result = new Matrix();
			result.setNumOfRows(m1.getNumOfRows());
			result.setNumOfCols(m2.getNumOfCols());
			float data[][] = new float[result.getNumOfRows()][result.getNumOfCols()];
			for (int i = 0; i < m1.getNumOfRows(); i++) {
				for (int j = 0; j < m2.getNumOfCols(); j++) {
					data[i][j] = 0.0f;
					for (int k = 0; k < m1.getNumOfCols(); k++) {
						data[i][j] += m1.getData()[i][k] * m2.getData()[k][j];
					}
				}
			}
			result.setData(data);
			return result;
		}
	}

	public String toString() {
		String result = "";
		for (int i = 0; i < this.numOfRows; i++) {
			for (int j = 0; j < this.numOfCols; j++) {
				result += Utilities.formatDecimal(this.data[i][j]) + "\t";
			}
			result += "\n";
		}
		return result;
	}

	public static void main(String[] args) {
		Matrix m1 = Utilities.readFromFile("a1.txt");
		Matrix m2 = Utilities.readFromFile("a2.txt");
		Matrix m3 = Utilities.readFromFile("a3.txt");
		Matrix m4 = Utilities.readFromFile("a4.txt");
		Matrix c = Utilities.readFromFile("c.txt");
		// for (int i = 0; i < m1.getNumOfRows(); i++){
		// System.out.print(Utilities.formatDecimal(m1.eigenvectors()[i]) +
		// "\t");
		// }
		// System.out.println();
		// for (int i = 0; i < m2.getNumOfRows(); i++){
		// System.out.print(Utilities.formatDecimal(m2.eigenvectors()[i]) +
		// "\t");
		// }
		// System.out.println();
		// for (int i = 0; i < m3.getNumOfRows(); i++){
		// System.out.print(Utilities.formatDecimal(m3.eigenvectors()[i]) +
		// "\t");
		// }
		// System.out.println();
		// for (int i = 0; i < m4.getNumOfRows(); i++){
		// System.out.print(Utilities.formatDecimal(m4.eigenvectors()[i]) +
		// "\t");
		// }
		// System.out.println();
		for (int i = 0; i < c.getNumOfRows(); i++) {
			System.out.print(Utilities.formatDecimal(c.eigenVector()[i]) + "\t");
		}
		System.out.println(c.eigenMatrix().toString());
		Matrix[] listMatrix = { m1, m2, m3, m4 };
		System.out.println(Utilities.createMatrix(listMatrix));
		Matrix result = Matrix.mutiply(Utilities.createMatrix(listMatrix), c.eigenMatrix());
		System.out.println(result);
	}
}
