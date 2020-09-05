package tk.valoeghese.sod;

import java.io.File;
import java.io.IOException;

class TestMain {

	public static void main(String[] args) throws IOException {
		System.out.println("Main Test:");
		writeTest();
		readTest();
		System.out.println("Gzip vs Non-Gzip Test:");
		writeTest2();
		readTest2();
	}

	static void readTest() throws IOException {
		File f = new File("../test.sod");

		if (!f.createNewFile()) {
			BinaryData bd = BinaryData.read(f);

			StringArrayDataSection ds0 = bd.getStringArray("StringArray");

			for (String s : ds0) {
				System.out.println(s);
			}

			DataSection ds1 = bd.get("DS1");

			for (Object i : ds1) {
				System.out.println(i);
			}

			DataSection ds2 = bd.get("yeet");

			for (Object i : ds2) {
				System.out.println(i);
			}

			ByteArrayDataSection ds3 = bd.getByteArray("ByteArray");

			for (byte i : ds3) {
				System.out.println(i);
			}
		}
	}

	static void writeTest2() throws IOException {
		File compressed = new File("../test2.gsod");
		compressed.createNewFile();
		File uncompressed = new File("../test2.sod");
		compressed.createNewFile();
		BinaryData bd = new BinaryData();

		//DataSection e = bd.getOrCreate("A");
		//e.writeBoolean(false);

		ByteArrayDataSection ds0 = new ByteArrayDataSection();
		for (byte i = 0; i < 16; ++i) {
			ds0.writeByte(i);
		}

		bd.put("Bytes", ds0);
		bd.write(uncompressed);
		bd.writeGzipped(compressed);
	}

	static void readTest2() throws IOException {
		System.out.println("non gzipped data");
		File f = new File("../test2.sod");

		if (!f.createNewFile()) {
			BinaryData bd = BinaryData.read(f);

			for (byte b : bd.getByteArray("Bytes")) {
				System.out.println(b);
			}
		}

		System.out.println("gzipped data");
		f = new File("../test2.gsod");

		if (!f.createNewFile()) {
			BinaryData bd = BinaryData.readGzipped(f);

			for (byte b : bd.getByteArray("Bytes")) {
				System.out.println(b);
			}
		}
	}

	static void writeTest() throws IOException {
		File f = new File("../test.sod");
		f.createNewFile();
		BinaryData bd = new BinaryData();

		StringArrayDataSection ds0 = new StringArrayDataSection();
		ds0.writeString("Once upon a time");
		ds0.writeString("There was a programmer who went by the username of \"Valoeghese\"");
		ds0.writeString("One day he updated his data format SOD to have array sections");
		bd.put("StringArray", ds0);

		DataSection ds1 = bd.getOrCreate("DS1");
		ds1.writeBoolean(false);
		ds1.writeDouble(0.666D);
		ds1.writeLong(69696969);
		ds1.writeString("yaY33T");

		DataSection ds2 = bd.getOrCreate("yeet");
		ds2.writeByte((byte) 4);
		ds2.writeFloat(1.3F);
		ds2.writeString("e");
		ds2.writeString("ff");

		ByteArrayDataSection ds3 = new ByteArrayDataSection();
		ds3.writeByte((byte)0);
		ds3.writeByte((byte)9);
		ds3.writeByte((byte)-10);
		bd.put("ByteArray", ds3);

		bd.write(f);
	}

}
