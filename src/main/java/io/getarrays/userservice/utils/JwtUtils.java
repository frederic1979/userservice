package io.getarrays.userservice.utils;

import com.auth0.jwt.algorithms.Algorithm;

public class JwtUtils {

    public static final Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

}
