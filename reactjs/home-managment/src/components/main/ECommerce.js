import React, { Component } from 'react';
import { Navbar, Container, Row, Col } from 'react-bootstrap';
import ProductList from './ProductList';
import ShoppingCart from './ShoppingCart';

class ECommerce extends Component {

  render() {
    return (
      <div>
        <Navbar bg="dark" variant="dark">
          <Navbar.Brand href="#home">E-Commerce</Navbar.Brand>
        </Navbar>
        <hr/>
        <Container>
          <Row>
            <Col sm={8}>
              <ProductList />
            </Col>
            <Col sm={4}>
              <ShoppingCart />
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}

export default ECommerce;