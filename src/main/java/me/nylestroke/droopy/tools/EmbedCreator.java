package me.nylestroke.droopy.tools;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.List;

public class EmbedCreator {
    private static final EmbedBuilder embed = new EmbedBuilder();

    public static MessageEmbed createEmbed (
            String title,
            String author,
            String authorLogo,
            String thumbnail,
            String fullImage,
            List<String[]> fields,
            String description
    )
    {
        // Reset the embed
        embed.clear();

        // Default variables
        embed.setColor(Color.decode("#6a40c9"));
        embed.setFooter("© 2022 Copyright nylestroke");

        try {
            if (title != null) {
                embed.setTitle(title);
            }

            if (author != null && authorLogo != null) {
                embed.setAuthor(author, null, authorLogo);
            }

            if (author != null && authorLogo == null) {
                embed.setAuthor(author);
            }

            if (thumbnail != null) {
                embed.setThumbnail(thumbnail);
            }

            if (fullImage != null) {
                embed.setImage(fullImage);
            }

            if (fields != null) {
                for (String[] value : fields) {
                    String[] field = new String[2];
                    System.arraycopy(value, 0, field, 0, value.length);
                    embed.addField(field[0], field[1], false);
                }
            }

            if(description != null) {
                embed.setDescription(description);
            }
        } catch(Exception exception) {
            Logger.error(exception);
        }

        return embed.build();
    }
}
