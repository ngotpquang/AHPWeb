package ahp.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Utilities {
	public static String formatDecimal(float number){
		NumberFormat numberFormat = new DecimalFormat("#0.000");
		return numberFormat.format(number);
	}
	public static Matrix readFromFile(String filePath){
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line = br.readLine();
			int numOfRows = Integer.parseInt(line.split(" ")[0]);
			int numOfCols = Integer.parseInt(line.split(" ")[1]);
			float [][] data = new float[numOfRows][numOfCols];
			for (int i = 0; i < numOfRows; i++){
				line = br.readLine();
				for (int j = 0; j < numOfCols; j++){
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
	
	public static Matrix createMatrix(Matrix[] listMatrix){
		int numOfRows = listMatrix[0].getNumOfRows();
		int numOfCols = listMatrix.length;
		float[][] data = new float[numOfRows][numOfCols];
		for (int i = 0; i < numOfRows; i++){
			for (int j = 0; j < numOfCols; j++){
				data[i][j] = listMatrix[j].eigenVector()[i];
			}
		}
		return new Matrix(data, numOfRows, numOfCols);
	}
	
	public static void main(String[] args) {
		Matrix m = readFromFile("a1.txt");
		for (int i = 0; i < m.getNumOfRows(); i++){
			for (int j = 0; j < m.getNumOfCols(); j++){
				System.out.print(m.getData()[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
