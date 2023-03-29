import React from 'react';
import { Link } from 'react-router-dom';
import { Container, Row, Col, Button } from 'react-bootstrap';
import { ReactComponent as FitnessIcon } from '../assets/icons/fitness_icon.svg';
import { ReactComponent as ProgressIcon } from '../assets/icons/progress_icon.svg';
import { ReactComponent as WorkoutIcon } from '../assets/icons/workout_icon.svg';
import './homepage.css';

const HomePage = () => {
    return (
        <Container fluid className="p-0">
            <Row className="align-items-center justify-content-center hero-image py-5">
                <Col xs={12} md={6} className="text-center text-md-start px-5">
                    <h1 className="display-4 fw-bold">Welcome to GymGenius</h1>
                    <p className="lead my-4">
                        The all-in-one fitness app that helps you create and manage your own workouts, track your progress, and achieve your fitness goals.
                    </p>
                    <Link className="mx-2" to="/register">
                        <Button variant="primary" size="lg">Get started</Button>
                    </Link>
                    <Link className="mx-2" to="/login">
                        <Button variant="secondary" size="lg">Log In</Button>
                    </Link>
                </Col>
                <Col xs={12} md={6} className="text-center px-5">
                    <FitnessIcon className="hero-icon" />
                </Col>
            </Row>
            <Row className="py-5">
                <Col xs={12} md={4} className="text-center px-5">
                    <WorkoutIcon className="feature-icon" />
                    <h2 className="my-4">Create and manage workouts</h2>
                    <p className="lead">
                        With GymGenius, you can easily create and customize your own workouts, add exercises and sets, and save them for future use.
                    </p>
                </Col>
                <Col xs={12} md={4} className="text-center px-5">
                    <ProgressIcon className="feature-icon" />
                    <h2 className="my-4">Track your progress</h2>
                    <p className="lead">
                        Keep track of your progress with GymGenius's built-in tracking system, which allows you to log your workouts and monitor your performance over time.
                    </p>
                </Col>
                <Col xs={12} md={4} className="text-center px-5">
                    <FitnessIcon className="feature-icon" />
                    <h2 className="my-4">Achieve your fitness goals</h2>
                    <p className="lead">
                        Whether you're looking to build muscle, lose weight, or just get in shape, GymGenius can help you achieve your fitness goals and stay motivated along the way.
                    </p>
                </Col>
            </Row>
        </Container>
    );
};


export default HomePage;