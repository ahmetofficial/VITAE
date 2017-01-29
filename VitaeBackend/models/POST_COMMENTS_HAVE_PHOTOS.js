/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('POST_COMMENTS_HAVE_PHOTOS', {
    comment_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'POST_COMMENTS',
        key: 'comment_id'
      }
    },
    photo_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'PHOTOS',
        key: 'photo_id'
      }
    }
  }, {
    tableName: 'POST_COMMENTS_HAVE_PHOTOS'
  });
};
