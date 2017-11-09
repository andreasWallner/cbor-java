package co.nstant.in.cbor.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import co.nstant.in.cbor.CborBuilder;
import co.nstant.in.cbor.model.Array;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.UnicodeString;

public class ArrayTagBuilderTest {
    @Test
    public void shouldAddTagged() {
        CborBuilder builder = new CborBuilder();
        List<DataItem> dataItems = builder.addArray()
                .addTagged(2).tagged(1).value("asdf")
                .end()
                .build();
        assertEquals(1, dataItems.size());
        assertTrue(dataItems.get(0) instanceof Array);
        Array array = (Array) dataItems.get(0);
        assertEquals(1, array.getDataItems().size());
        assertTrue(array.getDataItems().get(0) instanceof UnicodeString);
        UnicodeString string = (UnicodeString) array.getDataItems().get(0);
        assertTrue(string.hasTag());
        assertEquals(1, string.getTag().getValue());
        assertTrue(string.getTag().hasTag());
        assertEquals(2, string.getTag().getTag().getValue());
    }
}
