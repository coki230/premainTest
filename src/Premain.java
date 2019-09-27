import java.lang.instrument.Instrumentation;

public class Premain {
    public static void premain(String args, Instrumentation inst) {
        inst.addTransformer(new TransformerTest());
    }
}
