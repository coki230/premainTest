import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.ProtectionDomain;

public class TransformerTest implements ClassFileTransformer {
    public static final String className2 = "D:\\work\\premainTest\\src\\TransClass2.class";
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (!className.contains("TransClass")) {
            return null;
        }
        System.out.println(className);
        return getByte();
    }

    public static byte[] getByte() {
        File file = new File(className2);
        byte[] bytes = null;
        try {
            FileInputStream fs = new FileInputStream(file);
            FileChannel channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while (channel.read(byteBuffer) > 0);
            bytes = byteBuffer.array();

            System.out.println(byteToHex(bytes));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * byte数组转hex
     * @param bytes
     * @return
     */
    public static String byteToHex(byte[] bytes){
        String strHex = "";
        StringBuilder sb = new StringBuilder("");
        for (int n = 0; n < bytes.length; n++) {
            strHex = Integer.toHexString(bytes[n] & 0xFF);
            sb.append((strHex.length() == 1) ? "0" + strHex.toUpperCase() : strHex.toUpperCase()); // 每个字节由两个字符表示，位数不够，高位补0
            if ((n + 1) % 16 == 0) {
                sb.append("\n");
            }
        }
        return sb.toString().trim();
    }
}
