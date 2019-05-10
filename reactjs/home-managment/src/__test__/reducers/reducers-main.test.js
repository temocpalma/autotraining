import { products, cart } from '../../reducers/reducers-main';

describe('products', () => {
  it('returns the initial state', () => {
    expect(products(undefined, {})).toEqual([]);
  });

  it('receives products', () => {
    const productList = [
      { id:1, name:"Product 1", price:100, image:"" }
    ]

    expect(
      products([], { type: "LOAD_PRODUCTS", products: productList })
    ).toEqual(productList);
  });
});

describe('cart', () => {
  it('returns the initial state', () => {
    expect(cart(undefined, {})).toEqual([]);
  });

  it('add product to cart when cart is empty', () => {
    const product = { id:1, name:"Product 1", price:100, image:"" };

    expect(
      cart([], { type: "ADD_TO_CART", product: product })
    ).toEqual([product]);
  });

  it('add product to cart when cart is not empty', () => {
    const currentProducts = [
      { id:1, name:"Product 1", price:100, image:"" },
      { id:2, name:"Product 2", price:50, image:"" }
    ];
    const newProduct = { id:3, name:"Product 3", price:150, image:"" };

    expect(
      cart(currentProducts, { type: "ADD_TO_CART", product: newProduct })
    ).toEqual(currentProducts.concat(newProduct));
  });

  it('remove product from cart', () => {
    const currentProducts = [
      { id:1, name:"Product 1", price:100, image:"" },
      { id:2, name:"Product 2", price:50, image:"" }
    ];
    const productToDelete = { id:1, name:"Product 1", price:100, image:"" };

    expect(
      cart(currentProducts, { type: "REMOVE_FROM_CART", product: productToDelete })
    ).toEqual(currentProducts.filter(p => p.id !== productToDelete.id));
  });
});