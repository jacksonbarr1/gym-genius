import React from 'react';
import { Card, Container, Row, Col} from 'react-bootstrap';
import { Avatar } from '@mui/material'
import {useSelector} from "react-redux";

const UserProfile = () => {
    const user = useSelector((state) => state.userAuthentication.user);
    console.log(user)
    return (
        <Container>
            <Row className="justify-content-center mt-5">
                <Col md={6}>
                    <Card>
                        <Card.Body>
                            <Row>
                                <Col className="d-flex justify-content-center">
                                    <Avatar
                                        alt={user.firstName + ' ' + user.lastName}
                                        src={user.profilePicture}
                                        sx={{ width: 100, height: 100}}
                                        />
                                </Col>
                            </Row>

                            <Row>
                                <Col className="d-flex justify-content-center">
                                    <h3>{user.firstName + user.lastName}</h3>
                                </Col>
                            </Row>

                            <Row>
                                <Col>

                                </Col>
                            </Row>
                        </Card.Body>


                    </Card>


                </Col>


            </Row>
        </Container>
    )
}

export default UserProfile;