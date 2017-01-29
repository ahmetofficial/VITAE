/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('USER_POSTS_HAVE_PHOTOS', {
    post_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'USER_POSTS',
        key: 'post_id'
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
    tableName: 'USER_POSTS_HAVE_PHOTOS'
  });
};
