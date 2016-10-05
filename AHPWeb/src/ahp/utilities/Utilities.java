package ahp.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author Quang Ngo TP
 *
 */
public class Utilities {
	public static float[] listOfN = { 0.0f, 0.0f, 0.0f, 0.58f, 0.90f, 1.12f, 1.24f, 1.32f, 1.41f };

	public static String formatDecimal(float number) {
		NumberFormat numberFormat = new DecimalFormat("#0.0000");
		return numberFormat.format(number);
	}

	public static Matrix readFromFile(String filePath) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line = br.readLine();
			int numOfRows = Integer.parseInt(line.split(" ")[0]);
			int numOfCols = Integer.parseInt(line.split(" ")[1]);
			float[][] data = new float[numOfRows][numOfCols];
			for (int i = 0; i < numOfRows; i++) {
				line = br.readLine();
				for (int j = 0; j < numOfCols; j++) {
					data[i][j] = Float.parseFloat(line.split(" ")[j]);
				}
			}
			br.close();
			return new Matrix(data, numOfRows, numOfCols);
		} catch (FileNotFoundException e) {
			System.out.println("File not found!!!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static Matrix createMatrix(Matrix[] listMatrix) {
		int numOfRows = listMatrix[0].getNumOfRows();
		int numOfCols = listMatrix.length;
		float[][] data = new float[numOfRows][numOfCols];
		for (int i = 0; i < numOfRows; i++) {
			for (int j = 0; j < numOfCols; j++) {
				data[i][j] = listMatrix[j].eigenVector()[i];
			}
		}
		return new Matrix(data, numOfRows, numOfCols);
	}
	
	public static float calculateCR(Matrix m1, Matrix m2){
		int numOfRows = m1.getNumOfRows();
		Matrix temp = Matrix.mutiply(m1, m2);
		float[]data = new float[numOfRows];
		for (int i = 0; i < numOfRows; i++) {
			data[i] = temp.getData()[i][0] / m2.getData()[i][0];
		}
		float lamda = 0.0f;
		for (int i = 0; i < numOfRows; i++){
			 lamda += data[i];
		}
		lamda = lamda / numOfRows;
		return (lamda - numOfRows) / (numOfRows - 1) / listOfN[numOfRows];
	}
	
	public static float getMaxInMatrixData(Matrix m){
		float max = m.getData()[0][0];
		for (int i = 0; i < m.getNumOfRows(); i++){
			for (int j = 0; j < m.getNumOfCols(); j++){
				if (max < m.getData()[i][j]){
					max = m.getData()[i][j];
				}
			}
		}
		return max;
	}
	
	public static float stringToFloat(String s){
		String[] temp = s.split("/");
		return Float.parseFloat(temp[0]) / Integer.parseInt(temp[1]);
	}

	public static void main(String[] args) {
//		Matrix m = readFromFile("a1.txt");
//		for (int i = 0; i < m.getNumOfRows(); i++) {
//			for (int j = 0; j < m.getNumOfCols(); j++) {
//				System.out.print(m.getData()[i][j] + "\t");
//			}
//			System.out.println();
//		}
		System.out.println(Utilities.formatDecimal(stringToFloat("1/9")));
	}
}
