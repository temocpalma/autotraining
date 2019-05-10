import { addToCart, loadProducts } from '../../components/actionCreators';
import configureStore from 'redux-mock-store';
import thunk from 'redux-thunk';
import moxios from 'moxios';

const middleWares = [thunk];
const mockStore = configureStore(middleWares);

beforeEach( () => moxios.install() );
afterEach( () => moxios.uninstall() );

it('adds to cart', () => {
  const store = mockStore({ cart: [] });

  const product = { id:1, name:"Product 1", price:100, image:"" };
  store.dispatch(addToCart(product));

  const actions = store.getActions();
  expect(actions.length).toBe(1);
  expect(actions[0].type).toBe("ADD_TO_CART");
  expect(actions[0].product).not.toBeNull();
  expect(actions[0].product.id).toBe(1);
});

it('loads products', () => {
  const store = mockStore({ products: [] });
  moxios.stubRequest('http://localhost:3001/products', {
    status: 200,
    response: [
      {"id": 1, "name": "Product 1", "price": 299, "image":"https://free-images.com/md/1d3b/mannequin_trade_garment_shirt.jpg"},
      {"id": 2, "name": "Product 2", "price": 99, "image":"https://free-images.com/md/70b2/white_shirt_vietnam_girl_1.jpg"},
      {"id": 3, "name": "Product 3", "price": 149, "image":"https://free-images.com/md/9774/shirts_things_top_view.jpg"}
    ]
  });

  return store.dispatch(loadProducts())
    .then( () => {
      const actions = store.getActions();
      expect(actions.length).toBe(1);
      expect(actions[0].type).toBe("LOAD_PRODUCTS");
      expect(actions[0].products).not.toBeNull();
      expect(actions[0].products.length).toBe(3);
    });
});