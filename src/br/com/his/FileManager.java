package br.com.his;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TMemoryBuffer;

import br.com.his.ficha.FichaConcrete;
import br.gov.saude.esusab.dadotransp.DadoTransporteThrift;

public class FileManager {

	
	public static List<DadoTransporteThrift> readFromZip(final String filePath) throws FileNotFoundException, IOException, TException {

        final List<DadoTransporteThrift> dados = new ArrayList<>();

        final File f = new File(filePath);

        try (InputStream file = new FileInputStream(f); ZipInputStream zis = new ZipInputStream(file);) {

            ZipEntry entry = zis.getNextEntry();

            if (entry != null) {
                do {
                    final byte[] buffer = new byte[4096];
                    final byte[] data = unzipEntry(zis, buffer);

                    final DadoTransporteThrift dadoTransporteThrift = new DadoTransporteThrift();
                    FileManager fileManager = new FileManager();
                    fileManager.unserializeBinaryProtocol(data, dadoTransporteThrift);
                    dados.add(dadoTransporteThrift);

                    entry = zis.getNextEntry();
                } while (entry != null);
            }
        }

        return dados;
    }
	
	public static byte[] unzipEntry(final ZipInputStream zipInputStream, final byte[] buffer) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int numBytes;
        while ((numBytes = zipInputStream.read(buffer, 0, buffer.length)) != -1) {
            baos.write(buffer, 0, numBytes);
        }

        zipInputStream.closeEntry();
        return baos.toByteArray();
    }
	
	public void unserializeBinaryProtocol(byte[] data,TBase<?, ? extends TFieldIdEnum> thrift) throws TException {
        try (TMemoryBuffer transport = new TMemoryBuffer(0);) {
            transport.write(data);
            TBinaryProtocol protocol = new TBinaryProtocol(transport);
            thrift.read(protocol);
        }
    }
	
}
