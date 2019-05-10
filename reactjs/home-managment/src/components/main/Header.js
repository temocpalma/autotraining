import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHome } from '@fortawesome/free-solid-svg-icons';

const Header = () => {
  return(
    <Container>
      <Row>
        <Col md={2}>
          <FontAwesomeIcon icon={faHome} size="5x"/>
        </Col>
        <Col md={10}>
          <h1 className="text-center">Home Managment</h1>
        </Col>
      </Row>
    </Container>
  );
}

export default Header;