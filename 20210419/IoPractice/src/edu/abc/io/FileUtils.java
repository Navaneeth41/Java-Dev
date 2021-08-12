package edu.abc.io;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Scanner;

public class FileUtils {

	public static final int DEFAUT_BUFFER_SIZE = 1024;

	public static String getAbsolutePath(File dataFile) {
		return dataFile.getAbsolutePath();
	}

	public static String getCanonicalPath(File dataFile) {
		String path = null;
		try {
			path = dataFile.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

	public static void createNewFile(File dataFile) throws IOException {
		if (!dataFile.exists()) {
			dataFile.createNewFile();
		}
	}

	/**
	 * Read Ascii charset text file by byte extract
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	private static String readLine(InputStream inputStream) {
		char c;
		int r;
		String s = null;
		try {
			while ((r = inputStream.read()) != -1) {
				c = (char) r;
				s = s == null ? "" + c : s + c;
				if (c == '\n') {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}

	private static String readLine(Reader reader) {
		char c;
		String s = null;
		int r;
		try {
			while ((r = reader.read()) != -1) {
				c = (char) r;
				s = s == null ? "" + c : s + c;
				if (c == '\n') {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}

	private static void print(InputStream inputStream) throws IOException {
		String s = null;
		do {
			s = readLine(inputStream);
			if (s != null) {
				System.out.print(s);
			}
		} while (s != null);
	}

	private static void print(Reader reader) throws IOException {
		String s = null;
		do {
			s = readLine(reader);
			if (s != null) {
				System.out.print(s);
			}
		} while (s != null);
	}

	public static void printFile(String filePath) {
		InputStream fileIo = null;
		InputStream fileIo2 = null;
		try {
			fileIo = new FileInputStream(filePath);

			// buffer for efficiency
			fileIo = new BufferedInputStream(fileIo, DEFAUT_BUFFER_SIZE);
			print(fileIo);
			//fileIo.reset();

			fileIo2 = new FileInputStream(filePath);

			Charset encoding = Charset.defaultCharset();
			Reader reader = new InputStreamReader(fileIo2, encoding);
			// buffer for efficiency
			reader = new BufferedReader(reader, DEFAUT_BUFFER_SIZE);
			print(reader);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fileIo != null) {
				try {
					fileIo.close();
				} catch (IOException e) {
				}
			}
			if (fileIo2 != null) {
				try {
					fileIo.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static void copyFile(String fromFilePath, String toFilePath) {
		InputStream is = null;
		OutputStream os = null;
		try {
			// createNewFile(new File(toFilePath));
			is = new FileInputStream(fromFilePath);
			os = new FileOutputStream(toFilePath);
			byte[] buffer = new byte[DEFAUT_BUFFER_SIZE];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {

			}
			try {
				if (os != null) {
					os.flush();
				}
			} catch (Exception e) {

			}
			try {
				if (os != null) {
					os.close();
				}
			} catch (Exception e) {
			}
		}
	}
	
	// use try-catch-resources to avoid finally block
	public static void copyFile2(String fromFilePath, String toFilePath) {
		try (InputStream is = new FileInputStream(fromFilePath);
				OutputStream os = new FileOutputStream(toFilePath);) {
			byte[] buffer = new byte[DEFAUT_BUFFER_SIZE];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// using java.util.scanner to read file
	public static void copyFile3(String fromFilePath, String toFilePath) {
		try (Scanner scanner = new Scanner(new File(fromFilePath));
				PrintWriter writer = new PrintWriter(new File(toFilePath))) {
			while (scanner.hasNext()) {
				writer.print(scanner.nextLine()+ "\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void writeObject(Serializable obj, String path) {
		FileOutputStream fileOut = null;
		ObjectOutputStream out = null;
		try {
			fileOut = new FileOutputStream(path);
			out = new ObjectOutputStream(fileOut);
			out.writeObject(obj);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileOut != null) {
				try {
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Object readObject(String path) {
		Object obj = null;
		FileInputStream fileIn = null;
		ObjectInputStream in = null;
		try {
			fileIn = new FileInputStream(path);
			in = new ObjectInputStream(fileIn);
			obj = in.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileIn != null) {
				try {
					fileIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return obj;
	}

	public static void main(String[] args) {
		// Use "." for present java process execution directory
		File dataFile = new File("./resources/data.txt");
		System.out.println("getAbsolutePath: " + getAbsolutePath(dataFile));
		System.out.println("getCanonicalPath: " + getCanonicalPath(dataFile));

		printFile(getCanonicalPath(dataFile));
		copyFile("./resources/data.txt", "./resources/data1.txt");
		copyFile2("./resources/data.txt", "./resources/data2.txt");
		copyFile3("./resources/data.txt", "./resources/data3.txt");
	}
}
