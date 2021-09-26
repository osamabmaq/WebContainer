package osama.learning.container.extractors;

public interface Extractor<T, E> {
    T extract(E e) throws Exception;
}
