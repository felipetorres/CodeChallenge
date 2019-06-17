package br.com.app.codechallenge.infra;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import androidx.annotation.NonNull;
import br.com.app.codechallenge.models.Product;
import br.com.app.codechallenge.models.Purchase;

public class Util {

    public static <Element> Double sum(List<Element> elements, Function<Element, Double> function) {
        return elements.stream().map(function).reduce((e1, e2) -> e1 + e2).orElse(0.0);
    }

    public static List<Purchase> flat(Collection<List<Purchase>> purchasesCollection) {
        return purchasesCollection.stream()
                .flatMap(List::stream)
                .map(purchase -> {
                    purchase.setPrice(purchase.getProduct().getPrice());
                    return purchase;
                    })
                .collect(Collectors.toList());
    }

    public static List<Purchase> add(@NonNull Product product, Map<String, List<Purchase>> purchases) {
        List<Purchase> list = new ArrayList<>(Objects.requireNonNull(purchases.get(product.getCode())));
        list.add(new Purchase(product));
        return list;
    }

    public static List<Purchase> remove(@NonNull Product product, Map<String, List<Purchase>> purchases) {
        List<Purchase> list = new ArrayList<>(Objects.requireNonNull(purchases.get(product.getCode())));
        list.remove(0);
        return list;
    }
}
