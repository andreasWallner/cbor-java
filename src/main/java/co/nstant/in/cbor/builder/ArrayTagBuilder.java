package co.nstant.in.cbor.builder;

import co.nstant.in.cbor.model.Array;
import co.nstant.in.cbor.model.DataItem;
import co.nstant.in.cbor.model.Map;
import co.nstant.in.cbor.model.Tag;

public class ArrayTagBuilder<T extends ArrayBuilder<?>> extends AbstractBuilder<T> {
    private final Tag tag;
    
    ArrayTagBuilder(T parent, Tag tag) {
        super(parent);
        this.tag = tag;
    }
    
    public T value(DataItem dataItem) {
        dataItem.setTag(tag);
        getParent().add(dataItem);
        return getParent();
    }
    
    public T value(long value) {
        value(convert(value));
        return getParent();
    }

    public T value(boolean value) {
        value(convert(value));
        return getParent();
    }

    public T value(float value) {
        value(convert(value));
        return getParent();
    }

    public T value(double value) {
        value(convert(value));
        return getParent();
    }

    public T value(byte[] bytes) {
        value(convert(bytes));
        return getParent();
    }

    public T value(String string) {
        value(convert(string));
        return getParent();
    }
    
    public ArrayBuilder<T> array() {
        Array array = new Array();
        array.setTag(tag);
        getParent().add(array);
        return new ArrayBuilder<T>(getParent(), array);
    }
    
    public ArrayBuilder<T> startArray() {
        Array array = new Array();
        array.setChunked(true);
        array.setTag(tag);
        getParent().add(array);
        return new ArrayBuilder<T>(getParent(), array);
    }
    
    public MapBuilder<T> map() {
        Map map = new Map();
        map.setTag(tag);
        getParent().add(map);
        return new MapBuilder<T>(getParent(), map);
    }
    
    public MapBuilder<T> startMap() {
        Map map = new Map();
        map.setChunked(true);
        map.setTag(tag);
        getParent().add(map);
        return new MapBuilder<T>(getParent(), map);
    }
    
    public ArrayTagBuilder<T> tagged(long value) {
        Tag newTag = tag(value);
        newTag.setTag(this.tag);
        return new ArrayTagBuilder<T>(getParent(), newTag);
    }
}
