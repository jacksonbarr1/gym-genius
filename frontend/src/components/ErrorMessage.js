import { useState, useEffect } from "react";

const ErrorMessage = ({ message, onClose}) => {
    const [isVisible, setIsVisible] = useState(true);

    useEffect(() => {
        const timer = setTimeout(() => {
            setIsVisible(false)
            onClose();
        }, 5000)

        return () => {
            clearTimeout(timer)
        }
    }, [onClose])

    return (
        <div className={`alert alert-danger ${isVisible ? 'visible' : 'hidden'}`} role="alert">
            <p className="mb-0">{message}</p>
            <button type="button" className="btn-close" aria-label="Close" onClick={onClose}></button>
        </div>
    )
}
export default ErrorMessage