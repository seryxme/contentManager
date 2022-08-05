package ng.hotsystems.contentManager.utils;

import ng.hotsystems.contentManager.data.models.User;
import ng.hotsystems.contentManager.dtos.requests.RegisterUserRequest;

public class Mapper {
    public static void map(RegisterUserRequest request, User user) {
        user.setUsername(request.getEmail());
        user.setPassword(request.getPassword());
    }
}
