import React from "react";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import Home from "./routes/home";
import Error from "./routes/error";
import Login from "./routes/login";
import Layout from "./components/layout";
import Portfolio from "./routes/portfolio";

function App() {
  const router = createBrowserRouter([
    {
      path: "/",
      element: <Layout />,
      errorElement: <Error />,
      children: [
        { index: true, element: <Home /> },
        { path: "/portfolio", element: <Portfolio /> },
      ],
    },
    {
      path: "/login",
      element: <Login />,
    },
  ]);

  return <RouterProvider router={router} />;
}

export default App;
